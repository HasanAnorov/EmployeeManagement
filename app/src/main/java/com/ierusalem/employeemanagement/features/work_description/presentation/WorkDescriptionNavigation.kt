package com.ierusalem.employeemanagement.features.work_description.presentation

sealed class WorkDescriptionNavigation {
    data object InvalidResponse: WorkDescriptionNavigation()
}

