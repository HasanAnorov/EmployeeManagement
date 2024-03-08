package com.ierusalem.employeemanagement.features.auth.data

import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_request.User

interface AuthorizationService {
    @POST("login")
    suspend fun loginUser(
        @Body body: User
    ): Response<AuthResponse>

}