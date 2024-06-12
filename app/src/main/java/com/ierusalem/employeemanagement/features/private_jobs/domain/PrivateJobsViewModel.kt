package com.ierusalem.employeemanagement.features.private_jobs.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.Result
import com.ierusalem.employeemanagement.features.private_jobs.data.PrivateJobRepository
import com.ierusalem.employeemanagement.features.private_jobs.presentation.PrivateJobsEvents
import com.ierusalem.employeemanagement.features.private_jobs.presentation.PrivateJobsNavigation
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.emitNavigation
import com.ierusalem.employeemanagement.core.utils.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PrivateJobsViewModel(private val repo: PrivateJobRepository): ViewModel(),
    NavigationEventDelegate<PrivateJobsNavigation> by DefaultNavigationEventDelegate() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(PrivateJobsNavigation.Failure)
    }

    private val _state: MutableStateFlow<PrivateJobsState> = MutableStateFlow(PrivateJobsState())
    val state = _state.asStateFlow()

    init {
        getCommands("yuborildi")
        getCommands("qabulqildi")
        getCommands("bajarildi")
        getCommands("bajarilmadi")
        getCommands("kechikibbajarildi")
    }

    fun handleEvents(event: PrivateJobsEvents){
        when(event){
            PrivateJobsEvents.NavIconClick -> {
                emitNavigation(PrivateJobsNavigation.NavIconClick)
            }
            is PrivateJobsEvents.TabItemClick -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = event.tabIndex
                    )
                }
            }
            is PrivateJobsEvents.OnPullToRefreshCommands -> {
                getCommands(event.status)
            }
            is PrivateJobsEvents.OnItemClick -> {
                emitNavigation(PrivateJobsNavigation.OnItemClick(event.workId))
            }
        }
    }


    private fun getCommands(status: String) {
        try {
            updateLoading(true)
            viewModelScope.launch(handler) {
                repo.getMessage(status).let { response ->
                    if (response.isSuccessful) {
                        updateLoading(false)
                        when (status) {
                            "yuborildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsSent = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "qabulqildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsReceived = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "bajarildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsDone = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "bajarilmadi" -> {
                                _state.update {
                                    it.copy(
                                        commandsNotDone = response.body()?.results ?: listOf()
                                    )
                                }
                            }

                            "kechikibbajarildi" -> {
                                _state.update {
                                    it.copy(
                                        commandsLateDone = response.body()?.results ?: listOf()
                                    )
                                }
                            }
                        }
                    } else {
                        updateLoading(false)
                    }
                }
            }
        } catch (e: Exception) {
            updateLoading(false)
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }


}

data class PrivateJobsState(
    val selectedTabIndex: Int = 0,
    val commandsSent: List<Result> = listOf(),
    val commandsReceived: List<Result> = listOf(),
    val commandsDone: List<Result> = listOf(),
    val commandsNotDone: List<Result> = listOf(),
    val commandsLateDone: List<Result> = listOf(),
    val isLoading: Boolean = false,
    val tabItems: List<UiText> = listOf(
        UiText.StringResource(resId = R.string.commands_sent),
        UiText.StringResource(resId = R.string.commands_received),
        UiText.StringResource(resId = R.string.commands_done),
        UiText.StringResource(resId = R.string.commands_not_done),
        UiText.StringResource(resId = R.string.late_done)
    ),
)