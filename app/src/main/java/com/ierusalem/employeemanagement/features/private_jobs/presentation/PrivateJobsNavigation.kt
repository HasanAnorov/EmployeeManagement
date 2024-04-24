package com.ierusalem.employeemanagement.features.private_jobs.presentation

sealed interface PrivateJobsNavigation  {
    data object Failure: PrivateJobsNavigation
    data object NavIconClick: PrivateJobsNavigation
    data class OnItemClick(val workId: String): PrivateJobsNavigation
}