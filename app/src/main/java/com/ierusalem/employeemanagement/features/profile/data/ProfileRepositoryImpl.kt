package com.ierusalem.employeemanagement.features.profile.data

import android.content.Context
import android.util.Log
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.profile.data.password_response.PasswordResponse
import com.ierusalem.employeemanagement.features.profile.domain.ProfileRepository
import com.ierusalem.employeemanagement.features.profile.presentation.ProfileScreenData
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class ProfileRepositoryImpl(
    private val preferences: PreferenceHelper,
    private val context: Context
) : ProfileRepository {

    override suspend fun getUser(): Response<ProfileScreenData> {
        Log.d("ahi3646_token", preferences.getToken())
        return RestClient(context = context).profileService.getUser(authToken = preferences.getToken())
    }

    override suspend fun setPassword(request: NewPasswordRequest): Response<PasswordResponse> {
        return RestClient(context = context).profileService.setPassword(
            authToken = preferences.getToken(),
            request = request
        )
    }

}