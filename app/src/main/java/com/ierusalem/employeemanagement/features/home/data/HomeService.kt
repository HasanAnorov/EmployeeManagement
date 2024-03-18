package com.ierusalem.employeemanagement.features.home.data

import com.ierusalem.employeemanagement.features.home.data.entity.UserLogoutRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface HomeService {

    @POST("logout")
    suspend fun logoutUser(
        @Header("Authorization") authToken: String,
        @Body body: UserLogoutRequest
    ): Response<Unit>

}