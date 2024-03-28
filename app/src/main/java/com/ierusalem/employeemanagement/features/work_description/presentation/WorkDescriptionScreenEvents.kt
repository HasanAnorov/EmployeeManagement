package com.ierusalem.employeemanagement.features.work_description.presentation

sealed class WorkDescriptionScreenEvents {
    data class MarkAsDone(val workId: String): WorkDescriptionScreenEvents()
    data object NavIconClick: WorkDescriptionScreenEvents()
    data class DownloadFile(val url: String): WorkDescriptionScreenEvents()
}