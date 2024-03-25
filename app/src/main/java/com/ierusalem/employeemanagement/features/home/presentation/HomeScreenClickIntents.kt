package com.ierusalem.employeemanagement.features.home.presentation

sealed interface HomeScreenClickIntents {
    data class TabItemClick(val tabIndex: Int): HomeScreenClickIntents
    data object OnPullToRefreshCommands: HomeScreenClickIntents
    data class CreateCommand(val userId: Int): HomeScreenClickIntents
    data object LogoutClick: HomeScreenClickIntents
}