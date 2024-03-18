package com.ierusalem.employeemanagement.features.home.data

import android.content.Context
import android.util.Log
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.home.data.entity.UserLogoutRequest
import com.ierusalem.employeemanagement.features.home.domain.HomeRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class HomeRepositoryImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
) : HomeRepository {
    override suspend fun logoutUser(): Response<Unit> {
        return RestClient(context).homeService.logoutUser(
            authToken = preferenceHelper.getToken(),
            body = UserLogoutRequest(refresh = preferenceHelper.getRefreshToken())
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