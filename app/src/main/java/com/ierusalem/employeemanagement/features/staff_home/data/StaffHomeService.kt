package com.ierusalem.employeemanagement.features.staff_home.data

import com.ierusalem.employeemanagement.features.home.data.UserLogoutRequest
import com.ierusalem.employeemanagement.features.profile.data.model.ProfileResponse
import com.ierusalem.employeemanagement.features.staff_home.data.model.response_messages.ResponseMessages
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface StaffHomeService {

    @GET("get_user_message")
    suspend fun getUserMessages(
        @Header("Authorization") authToken: String,
        @Query("status") status: String
    ): Response<ResponseMessages>

    @GET("profile")
    suspend fun getUser(
        @Header("Authorization") authToken: String,
    ): Response<ProfileResponse>

    @POST("logout")
    suspend fun logoutUser(
        @Header("Authorization") authToken: String,
        @Body body: UserLogoutRequest
    ): Response<Unit>

}