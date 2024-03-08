package com.ierusalem.employeemanagement.app

import android.app.Application
import com.ierusalem.employeemanagement.features.auth.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EmployeeManagementApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EmployeeManagementApp)
            modules(
                appModule
            )
        }

    }

}