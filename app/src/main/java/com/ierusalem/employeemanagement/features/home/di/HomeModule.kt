package com.ierusalem.employeemanagement.features.home.di

import com.ierusalem.employeemanagement.features.home.data.HomeRepositoryImpl
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.features.home.presentation.HomeViewModel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { PreferenceHelper(context = get()) }
    single<HomeRepository> { HomeRepositoryImpl(preferenceHelper = get(), context = get()) }
    viewModel { HomeViewModel(get()) }
}