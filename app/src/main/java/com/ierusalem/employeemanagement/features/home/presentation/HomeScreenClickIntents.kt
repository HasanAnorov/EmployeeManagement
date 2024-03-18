package com.ierusalem.employeemanagement.features.home.presentation

sealed interface HomeScreenClickIntents {
    data class TabItemClick(val tabIndex: Int): HomeScreenClickIntents
    data object LogoutClick: HomeScreenClickIntents
    data object FabButtonClick : HomeScreenClickIntents
}