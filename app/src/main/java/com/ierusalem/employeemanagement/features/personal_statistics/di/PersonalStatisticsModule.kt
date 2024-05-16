package com.ierusalem.employeemanagement.features.personal_statistics.di

import com.ierusalem.employeemanagement.features.personal_statistics.domain.PersonalStatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val personalStatisticsModule = module {
    viewModel { PersonalStatisticsViewModel() }
}