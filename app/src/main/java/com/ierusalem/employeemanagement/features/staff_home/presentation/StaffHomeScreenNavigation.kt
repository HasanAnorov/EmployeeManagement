package com.ierusalem.employeemanagement.features.staff_home.presentation

sealed class StaffHomeScreenNavigation {
    data object InvalidResponse: StaffHomeScreenNavigation()
    data object NavigateToLogin: StaffHomeScreenNavigation()
    data object FailedToLogout: StaffHomeScreenNavigation()
    data class OnItemClick(val workId: String): StaffHomeScreenNavigation()
}