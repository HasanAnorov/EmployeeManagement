package com.ierusalem.employeemanagement.features.auth.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_request.User
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.AuthResponse
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class AuthRepositoryImpl(
    private val preferences: PreferenceHelper,
    private val context: Context
    //private val api: AuthorizationService
) : AuthRepository {

    override suspend fun loginUser(username: String, password: String): Response<AuthResponse> {
        return RestClient(context).authService.loginUser(User(username, password))
    }

    override fun saveToken(token: String) {
        preferences.saveToken(token)
    }

    override fun saveAuthenticatedUser(username: String) {
        preferences.saveAuthenticatedUser(username)
    }

    override fun isLogged(): Boolean = preferences.isLogged()

}