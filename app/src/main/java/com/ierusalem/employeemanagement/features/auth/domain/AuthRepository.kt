package com.ierusalem.employeemanagement.features.auth.domain

import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.AuthResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun loginUser(username: String, password: String): Response<AuthResponse>
    fun saveToken(token:String)
    fun isLogged(): Boolean
}