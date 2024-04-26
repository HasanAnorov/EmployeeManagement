package com.ierusalem.employeemanagement.features.statistics.di

import com.ierusalem.employeemanagement.features.statistics.data.StatisticsRepository
import com.ierusalem.employeemanagement.features.statistics.data.StatisticsRepositoryImpl
import com.ierusalem.employeemanagement.features.statistics.domain.StatisticsViewModel
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val statisticsModule = module {
    single { PreferenceHelper(context = get()) }
    single<StatisticsRepository> { StatisticsRepositoryImpl(preferenceHelper = get(), context = get()) }
    viewModel { StatisticsViewModel(get()) }
}
