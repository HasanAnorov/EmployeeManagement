package com.ierusalem.employeemanagement.features.profile.data

import com.ierusalem.employeemanagement.features.profile.data.password_response.PasswordResponse
import com.ierusalem.employeemanagement.features.profile.presentation.ProfileScreenData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ProfileService {
    @GET("profile")
    suspend fun getUser(
        @Header("Authorization") authToken: String,
    ): Response<ProfileScreenData>

    @POST("set-password")
    suspend fun setPassword(
        @Header("Authorization") authToken: String,
        @Body request: NewPasswordRequest
    ): Response<PasswordResponse>

}