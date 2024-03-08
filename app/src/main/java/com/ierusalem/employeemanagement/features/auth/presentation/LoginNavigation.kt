package com.ierusalem.employeemanagement.features.auth.presentation


sealed interface LoginNavigation {
    data object NavigateToMain: LoginNavigation
    data object InvalidResponse: LoginNavigation
}