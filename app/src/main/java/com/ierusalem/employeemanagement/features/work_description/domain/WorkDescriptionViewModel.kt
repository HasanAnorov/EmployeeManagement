package com.ierusalem.employeemanagement.features.work_description.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.features.work_description.data.model.work.WorkItem
import com.ierusalem.employeemanagement.features.work_description.presentation.WorkDescriptionNavigation
import com.ierusalem.employeemanagement.features.work_description.presentation.WorkDescriptionScreenEvents
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class WorkDescriptionViewModel(
    private val repo: WorkDescriptionRepository,
) : ViewModel(),
    NavigationEventDelegate<WorkDescriptionNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<WorkDescriptionScreenState> =
        MutableStateFlow(WorkDescriptionScreenState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(WorkDescriptionNavigation.InvalidResponse)
    }

    fun showAlertDialog(isShown: Boolean){
        _state.update {
            it.copy(
                showAlertDialog = isShown
            )
        }
    }

    fun handleEvents(event: WorkDescriptionScreenEvents){
        when(event){
            is WorkDescriptionScreenEvents.OnTextChanged -> {
                onTextFormChanged(event.text)
            }
            WorkDescriptionScreenEvents.OnAttachFilesClick -> {
                emitNavigation(WorkDescriptionNavigation.AttachFileClick)
            }
            is WorkDescriptionScreenEvents.MarkAsDone -> { markAsDone(event.workId) }
            is WorkDescriptionScreenEvents.DownloadFile -> {
                emitNavigation(WorkDescriptionNavigation.DownloadFile(event.url))
            }
            WorkDescriptionScreenEvents.NavIconClick -> emitNavigation(WorkDescriptionNavigation.NavIconClick)
        }
    }

    fun isFromHome(isHome: Boolean){
        _state.update {
            it.copy(
                isFromHome = isHome
            )
        }
    }

    private fun markAsDone(workId: String){
        val requestBodyBuilder = MultipartBody.Builder()
        requestBodyBuilder.setType(MultipartBody.FORM)
        requestBodyBuilder.addFormDataPart("text_employee", state.value.textForm)
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
                repo.markAsDone(
                    workId = workId,
                    body = requestBody
                ).let {response ->
                    if(response.isSuccessful){
                        emitNavigation(WorkDescriptionNavigation.MarkedAsDone)
                    }else{
                        Log.d("ahi3646", "markAsDone else error: ${response.errorBody()} ")
                        emitNavigation(WorkDescriptionNavigation.InvalidResponseMarkAsDone)
                    }
                }
            }
        }catch (e:Exception){
            emitNavigation(WorkDescriptionNavigation.InvalidResponseMarkAsDone)
        }
    }

    fun getMessageByIdAdmin(workId: String){
        try {
            viewModelScope.launch(handler) {
                repo.getMessageByIdAdmin(workId).let { response ->
                    if (response.isSuccessful){
                        Log.d("ahi3646", "getMessageById: ${response.body()!!} ")
                        _state.update {
                            it.copy(
                                workItem = Resource.Success(response.body()!!)
                            )
                        }
                    }else{
                        Log.d("ahi3646", "getMessageById: error ")
                        _state.update {
                            it.copy(
                                workItem = Resource.Failure("Something went wrong!")
                            )
                        }
                    }
                }
            }
        }catch (e: Exception){
            Log.d("ahi3646", "getMessageById: error catch")
            _state.update {
                it.copy(
                    workItem = Resource.Failure("Something went wrong!")
                )
            }
        }
    }

    fun getMessageById(workId: String){
        try {
            viewModelScope.launch(handler) {
                repo.getMessageById(workId).let { response ->
                    if (response.isSuccessful){
                        Log.d("ahi3646", "getMessageById: ${response.body()!!} ")
                        _state.update {
                           it.copy(
                               workItem = Resource.Success(response.body()!!)
                           )
                        }
                    }else{
                        Log.d("ahi3646", "getMessageById: error ")
                        _state.update {
                            it.copy(
                                workItem = Resource.Failure("Something went wrong!")
                            )
                        }
                    }
                }
            }
        }catch (e: Exception){
            Log.d("ahi3646", "getMessageById: error catch")
            _state.update {
                it.copy(
                    workItem = Resource.Failure("Something went wrong!")
                )
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

    private fun onTextFormChanged(textForm: String) {
        _state.update {
            it.copy(
                textForm = textForm
            )
        }
    }
}


@Immutable
data class WorkDescriptionScreenState(
    val showAlertDialog: Boolean = false,
    val workItem: Resource<WorkItem> = Resource.Loading(),
    val isFromHome: Boolean = false,
    val textForm: String = "",
    val files: List<File> = arrayListOf()
)