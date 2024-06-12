package com.ierusalem.employeemanagement.features.profile.di

import com.ierusalem.employeemanagement.features.profile.data.ProfileRepositoryImpl
import com.ierusalem.employeemanagement.features.profile.domain.ProfileRepository
import com.ierusalem.employeemanagement.features.profile.presentation.ProfileViewModel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    single { PreferenceHelper(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
    viewModel {
        ProfileViewModel(get())
    }
}