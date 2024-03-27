package com.ierusalem.employeemanagement.features.edit_profile.di

import com.ierusalem.employeemanagement.features.edit_profile.data.EditProfileRepositoryImpl
import com.ierusalem.employeemanagement.features.edit_profile.domain.EditProfileRepository
import com.ierusalem.employeemanagement.features.edit_profile.presentation.EditProfileViewModel
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editProfileViewModule = module {
    single { PreferenceHelper(context = get()) }
    single<EditProfileRepository> { EditProfileRepositoryImpl(get(), get()) }
    viewModel {
        EditProfileViewModel(get())
    }
}