package com.ierusalem.employeemanagement.features.edit_work.di

import com.ierusalem.employeemanagement.features.edit_work.data.EditWorkRepositoryImpl
import com.ierusalem.employeemanagement.features.edit_work.domain.EditWorkRepository
import com.ierusalem.employeemanagement.features.edit_work.domain.EditWorkViewModel
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editWorkModule = module {
    single { PreferenceHelper(context = get()) }
    single<EditWorkRepository> { EditWorkRepositoryImpl(get(), get()) }
    viewModel {
        EditWorkViewModel(get())
    }
}