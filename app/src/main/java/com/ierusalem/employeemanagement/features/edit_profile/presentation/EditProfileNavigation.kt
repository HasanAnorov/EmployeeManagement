package com.ierusalem.employeemanagement.features.edit_profile.presentation

sealed interface EditProfileNavigation {
    data object NavigateToMain: EditProfileNavigation
    data object InvalidResponse: EditProfileNavigation
    data object Failure: EditProfileNavigation
}