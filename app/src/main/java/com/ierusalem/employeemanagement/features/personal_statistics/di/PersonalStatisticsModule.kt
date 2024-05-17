package com.ierusalem.employeemanagement.features.personal_statistics.di

import com.ierusalem.employeemanagement.features.personal_statistics.data.PersonalStatisticsImpl
import com.ierusalem.employeemanagement.features.personal_statistics.data.PersonalStatisticsRepository
import com.ierusalem.employeemanagement.features.personal_statistics.domain.PersonalStatisticsViewModel
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val personalStatisticsModule = module {
    single { PreferenceHelper(context = get()) }
    single<PersonalStatisticsRepository> { PersonalStatisticsImpl(preferenceHelper = get(), context = get()) }
    viewModel { PersonalStatisticsViewModel(get()) }
}