package com.ierusalem.employeemanagement.features.profile.data

import com.ierusalem.employeemanagement.features.profile.presentation.ProfileScreenData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {
    @GET("profile")
    suspend fun getUser(
        @Header("Authorization") authToken: String,
        //@Path("username") username: String
    ): Response<ProfileScreenData>

}