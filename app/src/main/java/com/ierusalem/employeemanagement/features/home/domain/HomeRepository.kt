package com.ierusalem.employeemanagement.features.home.domain

import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.CommandsResponse
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.EmployeesResponse
import retrofit2.Response

interface HomeRepository {
    suspend fun logoutUser():Response<Unit>
    suspend fun getMessage(status: String): Response<CommandsResponse>
    suspend fun getEmployees(): Response<EmployeesResponse>
    fun deleteToken()
    fun deleteRefreshToken()
}