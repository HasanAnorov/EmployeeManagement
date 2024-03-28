package com.ierusalem.employeemanagement.features.staff_home.presentation

sealed class StaffHomeScreenEvents {
    data class TabItemClick(val tabIndex: Int): StaffHomeScreenEvents()
    data class OnItemClick(val workId: String): StaffHomeScreenEvents()
    data class OnPullToRefreshCommands(val status: String): StaffHomeScreenEvents()
    data object LogoutClick: StaffHomeScreenEvents()
}