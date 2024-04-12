package com.ierusalem.employeemanagement.features.auth.domain

import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.AuthResponse
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User
import retrofit2.Response

interface AuthRepository {
    suspend fun loginUser(username: String, password: String, token: String): Response<AuthResponse>
    fun saveToken(token:String)
    fun saveRefreshToken(token:String)
    fun saveAuthenticatedUser(user: User)
    fun isLogged(): Boolean
}