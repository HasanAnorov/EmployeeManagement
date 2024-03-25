package com.ierusalem.employeemanagement.app

import android.app.Application
import com.ierusalem.employeemanagement.features.auth.di.authModel
import com.ierusalem.employeemanagement.features.compose.di.composeModule
import com.ierusalem.employeemanagement.features.edit_profile.di.editProfileViewModule
import com.ierusalem.employeemanagement.features.home.di.homeModule
import com.ierusalem.employeemanagement.features.profile.di.profileModule
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
                //networkModule,
                authModel,
                homeModule,
                profileModule,
                editProfileViewModule,
                composeModule
            )
        }
    }
}