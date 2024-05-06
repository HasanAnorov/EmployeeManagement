package com.ierusalem.employeemanagement.features.staff_for_information.presentation

sealed interface StaffForInformationEvents {
    data object NavIconClick: StaffForInformationEvents
    data object OnPullRefresh: StaffForInformationEvents
}