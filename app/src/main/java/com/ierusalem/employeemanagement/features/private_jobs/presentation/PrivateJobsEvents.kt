package com.ierusalem.employeemanagement.features.private_jobs.presentation

sealed interface PrivateJobsEvents {
    data class TabItemClick(val tabIndex: Int) : PrivateJobsEvents
    data class OnPullToRefreshCommands(val status: String) : PrivateJobsEvents
    data class OnItemClick(val workId: String,val status: String) : PrivateJobsEvents
    data object NavIconClick : PrivateJobsEvents
}