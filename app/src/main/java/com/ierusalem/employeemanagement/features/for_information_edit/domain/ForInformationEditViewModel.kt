package com.ierusalem.employeemanagement.features.for_information_edit.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.for_information_edit.presentation.EditInformationRepository
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

class ForInformationEditViewModel(
    private val editInformationRepository: EditInformationRepository
): ViewModel(),
    NavigationEventDelegate<ForInformationEditNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<ForInformationEditState> = MutableStateFlow(ForInformationEditState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(ForInformationEditNavigation.Failure)
    }

    fun updateUserId(userId: String){
        _state.update {
            it.copy(
                userId = userId
            )
        }
    }

    fun updateInformationId(informationId: String){
        _state.update {
            it.copy(
                informationId = informationId
            )
        }
    }

    fun submitEditedInformation(){
        val requestBodyBuilder = MultipartBody.Builder()
        requestBodyBuilder.setType(MultipartBody.FORM)
        requestBodyBuilder.addFormDataPart("user", state.value.userId)
        requestBodyBuilder.addFormDataPart("text", state.value.textForm)
        requestBodyBuilder.addFormDataPart("text", state.value.textForm)
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

        viewModelScope.launch(handler) {
            editInformationRepository.saveEditedInformation(requestBody, state.value.informationId).let {
                if (it.isSuccessful){
                    emitNavigation(ForInformationEditNavigation.EditedSuccessfully)
                }else{
                    emitNavigation(ForInformationEditNavigation.Failure)
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


}

data class ForInformationEditState(
    val userId: String = "",
    val informationId: String = "",
    val showAlertDialog: Boolean = false,
    val textForm: String = "",
    val files: List<File> = arrayListOf(),
    var isSubmitting: Boolean = false
)