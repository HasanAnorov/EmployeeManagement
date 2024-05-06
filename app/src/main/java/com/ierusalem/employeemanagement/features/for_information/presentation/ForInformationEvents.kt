package com.ierusalem.employeemanagement.features.for_information.presentation

sealed interface ForInformationEvents {
    data object NavIconClick : ForInformationEvents
    data object OnPullRefresh : ForInformationEvents
    data class OnTabItemClick(val tabIndex: Int) : ForInformationEvents
}