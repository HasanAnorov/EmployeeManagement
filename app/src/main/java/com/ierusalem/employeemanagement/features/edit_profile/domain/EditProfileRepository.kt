package com.ierusalem.employeemanagement.features.edit_profile.domain

import com.ierusalem.employeemanagement.features.edit_profile.data.response_model.ResponseModel
import okhttp3.RequestBody
import retrofit2.Response

interface EditProfileRepository {
    suspend fun updateProfile(body: RequestBody): Response<ResponseModel>
    suspend fun updateFirebaseToken(token: String): Response<Unit>
}