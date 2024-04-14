package com.ierusalem.employeemanagement.features.home.presentation


sealed interface HomeScreenNavigation {
    data class NavigateToCompose(val userId: Int): HomeScreenNavigation
    data class CallEmployee(val phoneNumber: String): HomeScreenNavigation
    data object NavigateToLogin: HomeScreenNavigation
    data object FailedToLogout: HomeScreenNavigation
    data object FailedToLoadEmployees: HomeScreenNavigation
    data object InvalidResponse: HomeScreenNavigation
    data class OnItemClick(val workId: String): HomeScreenNavigation
}