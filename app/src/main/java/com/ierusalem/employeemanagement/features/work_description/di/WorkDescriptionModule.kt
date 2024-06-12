package com.ierusalem.employeemanagement.features.work_description.di

import com.ierusalem.employeemanagement.features.work_description.data.WorkDescriptionRepositoryImpl
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionRepository
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionViewModel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val workDescriptionModule = module {
    single { PreferenceHelper(get()) }
    single<WorkDescriptionRepository> { WorkDescriptionRepositoryImpl(get(), get()) }
    viewModel { WorkDescriptionViewModel(get()) }
}