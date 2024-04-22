package com.ierusalem.employeemanagement.features.home.presentation

sealed interface HomeScreenClickIntents {
    data class TabItemClick(val tabIndex: Int) : HomeScreenClickIntents
    data class OnPullToRefreshCommands(val status: String) : HomeScreenClickIntents
    data object OnPullToRefreshEmployees : HomeScreenClickIntents
    data class CreateCommand(val userId: Int) : HomeScreenClickIntents
    data class CreateCommands(val users: List<String>) : HomeScreenClickIntents
    data class OnEmployeeClick(val userId: Int) : HomeScreenClickIntents
    data class CallEmployee(val phoneNumber: String) : HomeScreenClickIntents
    data class OnItemClick(val workId: String) : HomeScreenClickIntents
    data class OnThemeChange(val isDarkTheme: Boolean) : HomeScreenClickIntents
    data object LogoutClick : HomeScreenClickIntents
    data object ClearEmployeesCommandsList : HomeScreenClickIntents
}