package com.ierusalem.employeemanagement.app

import android.app.Application
import com.ierusalem.employeemanagement.features.auth.di.AuthContainer


class EmployeeManagementApp: Application() {

    val appContainer = AppContainer(applicationContext)
    val authContainer = AuthContainer(appContainer)

}