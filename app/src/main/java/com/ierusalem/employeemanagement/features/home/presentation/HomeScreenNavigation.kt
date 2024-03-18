package com.ierusalem.employeemanagement.features.home.presentation

sealed interface HomeScreenNavigation {
    data object NavigateToPrivate: HomeScreenNavigation
    data object NavigateToCompose: HomeScreenNavigation
    data object NavigateToLogin: HomeScreenNavigation
    data object FailedToLogout: HomeScreenNavigation
}