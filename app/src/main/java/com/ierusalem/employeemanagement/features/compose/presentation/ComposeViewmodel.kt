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

    fun onSubmitClicked(userId: String){
        val requestBodyBuilder = MultipartBody.Builder()
        requestBodyBuilder.setType(MultipartBody.FORM)
        requestBodyBuilder.addFormDataPart("user", userId)
        requestBodyBuilder.addFormDataPart("text", state.value.textForm)
        for (file in state.value.files){
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
                    if(it.isSuccessful){
                        emitNavigation(ComposeScreenNavigation.Success)
                    }else{
                        emitNavigation(ComposeScreenNavigation.InvalidResponse)
                    }
                }
            }
        }catch (e: Exception){
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
    val files: List<File> = arrayListOf()
)
