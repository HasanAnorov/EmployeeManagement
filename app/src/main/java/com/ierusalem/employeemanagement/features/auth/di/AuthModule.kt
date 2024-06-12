package com.ierusalem.employeemanagement.features.auth.di

import com.ierusalem.employeemanagement.features.auth.data.AuthRepositoryImpl
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.features.auth.presentation.LoginViewModel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModel = module {
    single { PreferenceHelper(get()) }
    single<AuthRepository> { AuthRepositoryImpl(preferences = get(), context = get()) }
    viewModel { LoginViewModel(get()) }
}
