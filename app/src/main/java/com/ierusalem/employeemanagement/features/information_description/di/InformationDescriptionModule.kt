package com.ierusalem.employeemanagement.features.information_description.di

import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepository
import com.ierusalem.employeemanagement.features.for_information.data.ForInformationRepositoryImpl
import com.ierusalem.employeemanagement.features.information_description.domain.InformationDescriptionViewmodel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val informationDescriptionModule = module {
    single { PreferenceHelper(context = get()) }
    single<ForInformationRepository> {
        ForInformationRepositoryImpl(
            preferenceHelper = get(),
            context = get()
        )
    }
    viewModel {
        InformationDescriptionViewmodel(
            get()
        )
    }
}