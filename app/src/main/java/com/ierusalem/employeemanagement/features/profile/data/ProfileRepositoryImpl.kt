package com.ierusalem.employeemanagement.features.profile.data

import android.content.Context
import com.ierusalem.employeemanagement.core.app.RestClient
import com.ierusalem.employeemanagement.features.profile.data.model.ProfileResponse
import com.ierusalem.employeemanagement.features.profile.data.password_response.PasswordResponse
import com.ierusalem.employeemanagement.features.profile.domain.ProfileRepository
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import retrofit2.Response

//Built by Khasan Anorov 1.04.2024

class ProfileRepositoryImpl(
    private val preferences: PreferenceHelper,
    private val context: Context
) : ProfileRepository {

    override suspend fun getUser(): Response<ProfileResponse> {
        return RestClient(context = context).profileService.getUser(authToken = preferences.getToken())
    }

    override suspend fun setPassword(request: NewPasswordRequest): Response<PasswordResponse> {
        return RestClient(context = context).profileService.setPassword(
            authToken = preferences.getToken(),
            request = request
        )
    }

}