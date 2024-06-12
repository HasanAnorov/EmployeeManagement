package com.ierusalem.employeemanagement.features.create_work.di

import com.ierusalem.employeemanagement.features.create_work.data.ComposeRepositoryImpl
import com.ierusalem.employeemanagement.features.create_work.domain.ComposeRepository
import com.ierusalem.employeemanagement.features.create_work.presentation.ComposeViewmodel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val composeModule = module {
    single { PreferenceHelper(get()) }
    single<ComposeRepository> { ComposeRepositoryImpl(get(), get()) }
    viewModel{
        ComposeViewmodel(get())
    }
}