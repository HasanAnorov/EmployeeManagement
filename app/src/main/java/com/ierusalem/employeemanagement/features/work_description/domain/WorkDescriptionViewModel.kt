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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkDescriptionViewModel(
    private val repo: WorkDescriptionRepository,
) : ViewModel(),
    NavigationEventDelegate<WorkDescriptionNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<WorkDescriptionScreenState> =
        MutableStateFlow(WorkDescriptionScreenState())
    val state = _state.asStateFlow()


    fun handleEvents(event: WorkDescriptionScreenEvents){
        when(event){
            is WorkDescriptionScreenEvents.MarkAsDone -> { markAsDone(event.workId) }
            is WorkDescriptionScreenEvents.DownloadFile -> {
                emitNavigation(WorkDescriptionNavigation.DownloadFile(event.url))
            }
            WorkDescriptionScreenEvents.NavIconClick -> emitNavigation(WorkDescriptionNavigation.NavIconClick)
        }
    }

    private fun markAsDone(workId: String){
        try {
            viewModelScope.launch {
                repo.markAsDone(workId).let {response ->
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

    fun getMessageById(workId: String){
        try {
            viewModelScope.launch {
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

}


@Immutable
data class WorkDescriptionScreenState(
    val workItem: Resource<WorkItem> = Resource.Loading()
)