package com.ierusalem.employeemanagement.features.for_information_edit.di

import com.ierusalem.employeemanagement.features.for_information_edit.domain.ForInformationEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val forInformationEditModule = module {
//    single { PreferenceHelper(context = get()) }
//    single<EditWorkRepository> { EditWorkRepositoryImpl(get(), get()) }
    viewModel {
        ForInformationEditViewModel()
    }
}
