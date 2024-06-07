package com.ierusalem.employeemanagement.features.edit_work.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
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

class EditWorkViewModel(
    private val editWorkRepository: EditWorkRepository
) : ViewModel(),
    NavigationEventDelegate<EditWorkScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<EditWorkUiState> = MutableStateFlow(EditWorkUiState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(EditWorkScreenNavigation.InvalidResponse)
    }

    fun setUserId(userId:String){
        _state.update {
            it.copy(
                userId = userId
            )
        }
    }

    fun setWorkId(workId:String){
        _state.update {
            it.copy(
                workId = workId
            )
        }
    }

    fun onSaveClicked(){
        Log.d("ahi3646", "onSaveClicked: ${state.value.userId} ")
        val time = "${state.value.yearForm}-${state.value.monthForm}-${state.value.dayForm}"
        val requestBodyBuilder = MultipartBody.Builder()
        requestBodyBuilder.setType(MultipartBody.FORM)
        requestBodyBuilder.addFormDataPart("user", state.value.userId)
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
        viewModelScope.launch {
            editWorkRepository.saveEditedWork(requestBody, state.value.workId).let {
                if(it.isSuccessful){
                    Log.d("ahi3646", "onSaveClicked: success ")
                    emitNavigation(EditWorkScreenNavigation.SuccessOnWorkEdition)
                }else{
                    Log.d("ahi3646", "onSaveClicked: failure ")
                    emitNavigation(EditWorkScreenNavigation.FailureOnWorkEdition)
                }
            }
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

    @Suppress("unused")
    fun deleteWorkById(workId: String) {
        viewModelScope.launch(handler) {
            editWorkRepository.deleteWorkById(workId).let {
                if (it.isSuccessful) {
                    emitNavigation(EditWorkScreenNavigation.SuccessOnWorkDeletion)
                } else {
                    emitNavigation(EditWorkScreenNavigation.FailureOnWorkDeletion)
                }
            }
        }
    }

    fun showAlertDialog(isShown: Boolean) {
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

}

sealed interface EditWorkScreenNavigation {
    data object InvalidResponse : EditWorkScreenNavigation
    data object SuccessOnWorkEdition : EditWorkScreenNavigation
    data object FailureOnWorkEdition : EditWorkScreenNavigation
    data object SuccessOnWorkDeletion : EditWorkScreenNavigation
    data object FailureOnWorkDeletion : EditWorkScreenNavigation
}


@Immutable
data class EditWorkUiState(
    val showAlertDialog: Boolean = false,
    val textForm: String = "",
    val yearForm: String = Calendar
        .getInstance(TimeZone.getDefault())
        .get(Calendar.YEAR)
        .toString(),
    val monthForm: String = "",
    val dayForm: String = "",
    val files: List<File> = arrayListOf(),

    val userId: String = "",
    val workId: String = "",

    val isEditing: Boolean = false
)