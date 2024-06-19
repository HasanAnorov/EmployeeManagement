package com.ierusalem.employeemanagement.features.for_information_edit.di

import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import com.ierusalem.employeemanagement.features.for_information_edit.data.EditInformationRepositoryImpl
import com.ierusalem.employeemanagement.features.for_information_edit.domain.ForInformationEditViewModel
import com.ierusalem.employeemanagement.features.for_information_edit.presentation.EditInformationRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val forInformationEditModule = module {
    single { PreferenceHelper(context = get()) }
    single<EditInformationRepository> { EditInformationRepositoryImpl(get(), get()) }
    viewModel {
        ForInformationEditViewModel(get())
    }
}
