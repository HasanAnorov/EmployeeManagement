package com.ierusalem.employeemanagement.features.profile.domain

import com.ierusalem.employeemanagement.features.profile.data.NewPasswordRequest
import com.ierusalem.employeemanagement.features.profile.data.model.ProfileResponse
import com.ierusalem.employeemanagement.features.profile.data.password_response.PasswordResponse
import retrofit2.Response

interface ProfileRepository {
    suspend fun getUser():Response<ProfileResponse>

    suspend fun setPassword(request: NewPasswordRequest): Response<PasswordResponse>

}