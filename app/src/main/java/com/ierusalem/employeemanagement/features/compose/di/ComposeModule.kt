package com.ierusalem.employeemanagement.features.compose.di

import com.ierusalem.employeemanagement.features.compose.data.ComposeRepositoryImpl
import com.ierusalem.employeemanagement.features.compose.domain.ComposeRepository
import com.ierusalem.employeemanagement.features.compose.presentation.ComposeViewmodel
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val composeModule = module {
    single { PreferenceHelper(get()) }
    single<ComposeRepository> { ComposeRepositoryImpl(get(), get()) }
    viewModel{
        ComposeViewmodel(get())
    }
}