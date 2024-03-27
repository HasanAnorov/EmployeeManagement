package com.ierusalem.employeemanagement.features.staff_home.presentation

sealed class StaffHomeScreenNavigation {
    data object InvalidResponse: StaffHomeScreenNavigation()
}