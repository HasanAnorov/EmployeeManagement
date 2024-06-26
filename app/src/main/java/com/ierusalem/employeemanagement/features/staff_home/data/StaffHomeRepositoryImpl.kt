package com.ierusalem.employeemanagement.features.staff_home.data

import android.content.Context
import android.util.Log
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.home.data.entity.UserLogoutRequest
import com.ierusalem.employeemanagement.features.staff_home.data.model.response_messages.ResponseMessages
import com.ierusalem.employeemanagement.features.staff_home.domain.StaffHomeRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class StaffHomeRepositoryImpl (
    private val context: Context,
    private val preferenceHelper: PreferenceHelper
): StaffHomeRepository{
    override suspend fun getUserMessages(status: String): Response<ResponseMessages> {
        return RestClient(context).staffHomeService.getUserMessages(
            authToken = preferenceHelper.getToken(),
            status = status
        )
    }

    override suspend fun logoutUser(): Response<Unit> {
        return RestClient(context).staffHomeService.logoutUser(
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