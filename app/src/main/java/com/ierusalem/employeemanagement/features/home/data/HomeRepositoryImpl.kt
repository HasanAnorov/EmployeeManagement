package com.ierusalem.employeemanagement.features.home.data

import android.content.Context
import android.util.Log
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.auth.data.entity.auth_response.User
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.CommandsResponse
import com.ierusalem.employeemanagement.features.home.presentation.employees.model.EmployeesResponse
import com.ierusalem.employeemanagement.features.profile.data.model.ProfileResponse
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class HomeRepositoryImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
) : HomeRepository {

    override fun getUserFromLocal(): User {
        return preferenceHelper.getUser()
    }
    override fun getTheme(): Boolean {
        return preferenceHelper.getTheme()
    }
    override fun saveTheme(isDarkTheme: Boolean) {
        preferenceHelper.saveTheme(isDarkTheme)
    }
    override suspend fun logoutUser(): Response<Unit> {
        return RestClient(context).homeService.logoutUser(
            authToken = preferenceHelper.getToken(),
            body = UserLogoutRequest(refresh = preferenceHelper.getRefreshToken())
        )
    }

    override suspend fun getUserForHome(): Response<ProfileResponse> {
        return RestClient(context = context).profileService.getUser(authToken = preferenceHelper.getToken())
    }

    override suspend fun getMessage(status: String): Response<CommandsResponse> {
        return RestClient(context).homeService.getCommand(
            authToken = preferenceHelper.getToken(),
            status = status
        )
    }

    override suspend fun getEmployees(): Response<EmployeesResponse> {
        return RestClient(context).homeService.getEmployees(
            authToken = preferenceHelper.getToken(),
        )
    }

    override fun deleteToken() {
        preferenceHelper.deleteToken()
        Log.d("ahi3646", "deleteToken: ${preferenceHelper.getToken()} ")
    }
    override fun deleteRefreshToken() {
        preferenceHelper.deleteRefreshToken()
        Log.d("ahi3646", "deleteToken: ${preferenceHelper.getToken()} ")
    }
}