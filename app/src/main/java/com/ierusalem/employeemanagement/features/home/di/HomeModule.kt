package com.ierusalem.employeemanagement.features.home.di

import com.ierusalem.employeemanagement.features.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel() }
}