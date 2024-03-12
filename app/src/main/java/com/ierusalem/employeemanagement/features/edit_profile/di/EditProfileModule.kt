package com.ierusalem.employeemanagement.features.edit_profile.di

import com.ierusalem.employeemanagement.features.edit_profile.presentation.EditProfileViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val editProfileViewModule = module {
    viewModel {
        EditProfileViewModel()
    }
}