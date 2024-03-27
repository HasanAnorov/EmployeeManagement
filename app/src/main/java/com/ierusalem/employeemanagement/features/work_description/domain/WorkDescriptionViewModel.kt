package com.ierusalem.employeemanagement.features.work_description.domain

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.ierusalem.employeemanagement.features.staff_home.presentation.StaffHomeScreenNavigation
import com.ierusalem.employeemanagement.features.work_description.presentation.WorkDescriptionNavigation
import com.ierusalem.employeemanagement.features.work_description.presentation.WorkDescriptionScreenEvents
import com.ierusalem.employeemanagement.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.employeemanagement.ui.navigation.NavigationEventDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkDescriptionViewModel(
    private val repo: WorkDescriptionRepository
) : ViewModel(),
    NavigationEventDelegate<WorkDescriptionNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<WorkDescriptionScreenState> =
        MutableStateFlow(WorkDescriptionScreenState())
    val state = _state.asStateFlow()

    private fun handleEvents(event: WorkDescriptionScreenEvents){
        when(event){
            WorkDescriptionScreenEvents.MarkAsDone -> {

            }
        }
    }

}


@Immutable
data class WorkDescriptionScreenState(
    val workId: String = ""
)