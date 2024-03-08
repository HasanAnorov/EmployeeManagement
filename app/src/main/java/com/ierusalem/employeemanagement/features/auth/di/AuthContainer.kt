package com.ierusalem.employeemanagement.features.auth.di

import com.ierusalem.employeemanagement.app.AppContainer
import com.ierusalem.employeemanagement.features.auth.data.AuthRepositoryImpl
import com.ierusalem.employeemanagement.features.auth.data.AuthorizationService
import com.ierusalem.employeemanagement.utils.PreferenceHelper

class AuthContainer(appContainer: AppContainer) {

    private val authService = appContainer.retrofit.create(AuthorizationService::class.java)
    private val preferenceHelper = PreferenceHelper(context = appContainer.applicationContext)
    val authRepository = AuthRepositoryImpl(preferences = preferenceHelper, api = authService)

}