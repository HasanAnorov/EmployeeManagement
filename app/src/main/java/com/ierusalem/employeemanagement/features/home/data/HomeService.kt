package com.ierusalem.employeemanagement.features.home.data

import com.ierusalem.employeemanagement.features.home.data.entity.UserLogoutRequest
import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.CommandsResponse
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.EmployeesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeService {

    @POST("logout")
    suspend fun logoutUser(
        @Header("Authorization") authToken: String,
        @Body body: UserLogoutRequest
    ): Response<Unit>

    @GET("get_message")
    suspend fun getCommand(
        @Header("Authorization") authToken: String,
        @Query("status") status: String
    ): Response<CommandsResponse>

    @GET("get_users")
    suspend fun getEmployees(
        @Header("Authorization") authToken: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<EmployeesResponse>

}