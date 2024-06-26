package com.ierusalem.employeemanagement.features.home.presentation

sealed interface HomeScreenClickIntents {
    data class TabItemClick(val tabIndex: Int): HomeScreenClickIntents
    data class OnPullToRefreshCommands(val status: String): HomeScreenClickIntents
    data class CreateCommand(val userId: Int): HomeScreenClickIntents
    data object LogoutClick: HomeScreenClickIntents
}