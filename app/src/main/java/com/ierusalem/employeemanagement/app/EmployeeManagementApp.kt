package com.ierusalem.employeemanagement.app

import android.app.Application
import com.ierusalem.employeemanagement.MainViewModel
import com.ierusalem.employeemanagement.features.auth.di.authModel
import com.ierusalem.employeemanagement.features.home.di.homeModule
import com.ierusalem.employeemanagement.features.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EmployeeManagementApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EmployeeManagementApp)
            modules(
                //networkModule,
                mainModule,
                authModel,
                homeModule,
                profileModule
            )
        }

    }

}

val mainModule = module {
    viewModel { MainViewModel() }
}