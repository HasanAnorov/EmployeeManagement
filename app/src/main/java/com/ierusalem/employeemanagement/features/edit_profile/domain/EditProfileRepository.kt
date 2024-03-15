package com.ierusalem.employeemanagement.features.edit_profile.domain

import com.ierusalem.employeemanagement.features.edit_profile.data.model.RequestModel
import com.ierusalem.employeemanagement.features.edit_profile.data.response_model.ResponseModel
import retrofit2.Response

interface EditProfileRepository {
    suspend fun updateProfile(body: RequestModel):Response<ResponseModel>
}