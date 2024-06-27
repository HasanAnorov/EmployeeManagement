package com.ierusalem.employeemanagement.features.create_work.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.create_work.domain.ComposeRepository
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.core.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
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

    fun showAlertDialog(isShown: Boolean){
        _state.update {
            it.copy(
                showAlertDialog = isShown
            )
        }
    }

    fun onTextFormChanged(textForm: String) {
        _state.update {
            it.copy(
                textForm = textForm
            )
        }
    }

    fun onYearChanged(year: String) {
        _state.update {
            it.copy(
                yearForm = year
            )
        }
    }

    fun onMonthChanged(month: String) {
        _state.update {
            it.copy(
                monthForm = month
            )
        }
    }

    fun onDayChanged(day: String) {
        _state.update {
            it.copy(
                dayForm = day
            )
        }
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(ComposeScreenNavigation.InvalidResponse)
    }

    fun onSubmitClicked(userId: String) {
        Log.d("ahi3646", "userID : $userId ")
        changeSubmitState(true)
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
            viewModelScope.launch(handler) {
                if(state.value.isCommand){
                    repo.postMessage(requestBody).let {
                        if (it.isSuccessful) {
                            Log.d("ahi3646", "onSubmitClicked: success ${it.body()} ")
                            changeSubmitState(false)
                            emitNavigation(ComposeScreenNavigation.Success)
                        } else {
                            Log.d("ahi3646", "onSubmitClicked: error ${it.errorBody()} ")
                            changeSubmitState(false)
                            emitNavigation(ComposeScreenNavigation.InvalidResponse)
                        }
                    }
                }else{
                    repo.postInformation(requestBody).let {
                        if (it.isSuccessful) {
                            changeSubmitState(false)
                            emitNavigation(ComposeScreenNavigation.Success)
                        } else {
                            changeSubmitState(false)
                            emitNavigation(ComposeScreenNavigation.InvalidResponse)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            changeSubmitState(false)
            emitNavigation(ComposeScreenNavigation.InvalidResponse)
        }
    }

    fun onSubmitClickedForUsers(users:List<String>) {
        changeSubmitState(true)
        val time = "${state.value.yearForm}-${state.value.monthForm}-${state.value.dayForm}"
        val requestBodyBuilder = MultipartBody.Builder()
        requestBodyBuilder.setType(MultipartBody.FORM)
        for (user in users){
            Log.d("ahi3646", "$user : ${user.filter { it.isDigit() }} ")
            requestBodyBuilder.addFormDataPart(user, user.filter { it.isDigit() })
        }
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
            viewModelScope.launch(handler) {
                repo.postMessage(requestBody).let {
                    if (it.isSuccessful) {
                        changeSubmitState(false)
                        emitNavigation(ComposeScreenNavigation.Success)
                    } else {
                        changeSubmitState(false)
                        emitNavigation(ComposeScreenNavigation.InvalidResponse)
                    }
                }
            }
        } catch (e: Exception) {
            changeSubmitState(false)
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

    private fun changeSubmitState(isSubmitting: Boolean){
        _state.update{
            it.copy(
                isSubmitting = isSubmitting
            )
        }
        if(isSubmitting){
            _state.update {
                it.copy(
                    submitString = UiText.StringResource(R.string.submitting)
                )
            }
        }else {
            if(state.value.isCommand){
                _state.update {
                    it.copy(
                        submitString = UiText.StringResource(R.string.submit_task)
                    )
                }
            }else {
                _state.update {
                    it.copy(
                        submitString = UiText.StringResource(R.string.submit_information)
                    )
                }
            }
        }
    }

    fun changeIsCommand(isCommand: Boolean){
        _state.update {
            it.copy(
                isCommand = isCommand
            )
        }
        if(isCommand){
            _state.update {
                it.copy(
                    submitString = UiText.StringResource(R.string.submit_task)
                )
            }
        }else {
            _state.update {
                it.copy(
                    submitString = UiText.StringResource(R.string.submit_information)
                )
            }
        }
    }

}

data class ComposeScreenState(
    val submitString: UiText = UiText.StringResource(R.string.submit_task),
    val isCommand: Boolean = true,
    val showAlertDialog: Boolean = false,
    val remoteToken: String = "",
    val textForm: String = "",
    val yearForm: String = Calendar
        .getInstance(TimeZone.getDefault())
        .get(Calendar.YEAR)
        .toString(),
    val monthForm: String = "",
    val dayForm: String = "",
    val files: List<File> = arrayListOf(),
    var isSubmitting: Boolean = false
)