package com.ierusalem.employeemanagement.features.auth.data

import android.content.Context
import android.util.Log
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_request.UserRequest
import com.ierusalem.employeemanagement.features.auth.domain.AuthRepository
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.AuthResponse
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class AuthRepositoryImpl(
    private val preferences: PreferenceHelper,
    private val context: Context
) : AuthRepository {

    override suspend fun loginUser(username: String, password: String): Response<AuthResponse> {
        return RestClient(context).authService.loginUser(
            UserRequest(
                email = username,
                password = password
            )
        )
    }

    override fun saveToken(token: String) {
        Log.d("ahi3646_token", "token: $token ")
        preferences.saveToken(token)
    }

    override fun saveAuthenticatedUser(user: User) {
        preferences.saveAuthenticatedUser(user)
    }

    override fun isLogged(): Boolean = preferences.isLogged()

}