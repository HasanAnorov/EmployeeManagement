package com.ierusalem.employeemanagement.features.private_jobs.di

import com.ierusalem.employeemanagement.features.private_jobs.data.PrivateJobRepository
import com.ierusalem.employeemanagement.features.private_jobs.data.PrivateJobRepositoryImpl
import com.ierusalem.employeemanagement.features.private_jobs.domain.PrivateJobsViewModel
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val privateJobsModule = module {
    single { PreferenceHelper(context = get()) }
    single<PrivateJobRepository> { PrivateJobRepositoryImpl(preferenceHelper = get(), context = get()) }
    viewModel { PrivateJobsViewModel(get()) }
}
