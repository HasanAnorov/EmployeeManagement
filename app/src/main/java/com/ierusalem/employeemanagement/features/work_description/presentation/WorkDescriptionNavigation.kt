package com.ierusalem.employeemanagement.features.work_description.presentation

sealed class WorkDescriptionNavigation {
    data object InvalidResponse: WorkDescriptionNavigation()
    data object SuccessOnWorkDeletion: WorkDescriptionNavigation()
    data object SuccessOnLettingEdit: WorkDescriptionNavigation()
    data object FailureOnWorkDeletion: WorkDescriptionNavigation()
    data object FailureOnLettingEdit: WorkDescriptionNavigation()
    data object InvalidResponseMarkAsDone: WorkDescriptionNavigation()
    data object AttachFileClick: WorkDescriptionNavigation()
    data object MarkedAsDone: WorkDescriptionNavigation()
    data class DownloadFile(val url: String): WorkDescriptionNavigation()
    data object NavIconClick: WorkDescriptionNavigation()
}

