package com.ierusalem.employeemanagement.features.profile.presentation

sealed interface ProfileNavigation {
    data object PasswordChanged: ProfileNavigation
    data object InvalidResponse: ProfileNavigation
}