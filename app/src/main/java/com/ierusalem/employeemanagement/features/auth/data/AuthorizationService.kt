package com.ierusalem.employeemanagement.features.auth.data

import com.ierusalem.employeemanagement.features.auth.data.entity.auth_request.UserLoginRequest
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST("login")
    suspend fun loginUser(
        @Body body: UserLoginRequest
    ): Response<AuthResponse>

}