package com.ierusalem.employeemanagement.features.staff_for_information.di

import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepository
import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepositoryImpl
import com.ierusalem.employeemanagement.features.staff_for_information.domain.StaffForInformationViewmodel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val staffInformationModule = module {
    single { PreferenceHelper(context = get()) }
    single<ForInformationRepository> {
        ForInformationRepositoryImpl(
            preferenceHelper = get(),
            context = get()
        )
    }
    viewModel {
        StaffForInformationViewmodel(get())
    }
}
