package com.ierusalem.employeemanagement.features.private_jobs.data

import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.CommandsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PrivateJobService {
    @GET("get_user_message")
    suspend fun getCommand(
        @Header("Authorization") authToken: String,
        @Query("status") status: String
    ): Response<CommandsResponse>
}