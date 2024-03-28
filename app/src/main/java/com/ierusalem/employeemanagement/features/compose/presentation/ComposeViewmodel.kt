package com.ierusalem.employeemanagement.features.compose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.compose.domain.ComposeRepository
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.Calendar
import java.util.TimeZone

class ComposeViewmodel(private val repo: ComposeRepository) : ViewModel(),
    NavigationEventDelegate<ComposeScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<ComposeScreenState> = MutableStateFlow(
        ComposeScreenState()
    )
    val state = _state.asStateFlow()

    fun onTextFormChanged(textForm: String) {
        _state.update {
            it.copy(
                textForm = textForm
            )
        }
    }

    fun onYearChanged(year: String){
        _state.update {
            it.copy(
                yearForm = year
            )
        }
    }

    fun onMonthChanged(month: String){
        _state.update {
            it.copy(
                monthForm = month
            )
        }
    }

    fun onDayChanged(day: String){
        _state.update {
            it.copy(
                dayForm = day
            )
        }
    }

    //fixme
    fun onSubmitClicked(userId: String) {
        val time = "${state.value.yearForm}-${state.value.monthForm}-${state.value.dayForm}"
        val requestBodyBuilder = MultipartBody.Builder()
        requestBodyBuilder.setType(MultipartBody.FORM)
        requestBodyBuilder.addFormDataPart("user", userId)
        requestBodyBuilder.addFormDataPart("text", state.value.textForm)
        requestBodyBuilder.addFormDataPart("end_time", time)
        for (file in state.value.files) {
            requestBodyBuilder.addFormDataPart(
                "file",
                file.name,
                file.asRequestBody(
                    "*/*".toMediaType()
                )
            )
        }
        val requestBody = requestBodyBuilder.build()
        try {
            viewModelScope.launch {
                repo.postMessage(requestBody).let {
                    if (it.isSuccessful) {
                        emitNavigation(ComposeScreenNavigation.Success)
                    } else {
                        emitNavigation(ComposeScreenNavigation.InvalidResponse)
                    }
                }
            }
        } catch (e: Exception) {
            emitNavigation(ComposeScreenNavigation.InvalidResponse)
        }
    }

    fun onFilesChanged(file: File) {
        val newFiles = state.value.files.toMutableList().apply {
            add(file)
        }
        _state.update {
            it.copy(
                files = newFiles
            )
        }
    }

}

data class ComposeScreenState(
    val textForm: String = "",
    val yearForm: String = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR).toString(),
    val monthForm: String = "",
    val dayForm: String = "",
    val files: List<File> = arrayListOf()
)
