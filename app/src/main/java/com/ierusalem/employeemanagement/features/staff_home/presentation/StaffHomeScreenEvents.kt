package com.ierusalem.employeemanagement.features.staff_home.presentation

import com.ierusalem.employeemanagement.features.home.presentation.HomeScreenClickIntents

sealed class StaffHomeScreenEvents {
    data class TabItemClick(val tabIndex: Int): StaffHomeScreenEvents()
    data class OnItemClick(val workId: String): StaffHomeScreenEvents()
    data class OnPullToRefreshCommands(val status: String): StaffHomeScreenEvents()
    data object LogoutClick: StaffHomeScreenEvents()
}