package com.ierusalem.employeemanagement.features.auth.data

import com.ierusalem.employeemanagement.features.auth.data.entity.auth_request.User
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.AuthResponse
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class AuthRepositoryImpl(
    private val preferences: PreferenceHelper,
    private val api: AuthorizationService
) : AuthRepository {

    override suspend fun loginUser(username: String, password: String): Response<AuthResponse> {
        return api.loginUser(User(username,password))
    }

    override fun saveToken(token: String) {
        preferences.saveToken(token)
    }

    override fun isLogged(): Boolean = preferences.isLogged()

}