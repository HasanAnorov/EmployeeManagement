package com.ierusalem.employeemanagement.features.home.domain

import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.CommandsResponse
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.EmployeesResponse
import com.ierusalem.employeemanagement.features.profile.data.model.ProfileResponse
import retrofit2.Response
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User

interface HomeRepository {
    suspend fun logoutUser():Response<Unit>
    suspend fun getMessage(status: String): Response<CommandsResponse>
    suspend fun getEmployees(page: Int, perPage: Int): Response<EmployeesResponse>
    fun deleteToken()
    fun getUserFromLocal(): User
    fun getTheme(): Boolean
    fun saveTheme(isDarkTheme: Boolean)
    suspend fun getUserForHome():Response<ProfileResponse>
    fun deleteRefreshToken()
}