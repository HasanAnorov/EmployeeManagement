package com.ierusalem.employeemanagement.features.staff_home.di

import com.ierusalem.employeemanagement.features.staff_home.data.StaffHomeRepositoryImpl
import com.ierusalem.employeemanagement.features.staff_home.domain.StaffHomeRepository
import com.ierusalem.employeemanagement.features.staff_home.domain.StaffHomeViewModel
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val staffHomeScreenModule = module {
    single { PreferenceHelper(get()) }
    single<StaffHomeRepository> { StaffHomeRepositoryImpl(get(), get()) }
    viewModel { StaffHomeViewModel(get()) }
}
