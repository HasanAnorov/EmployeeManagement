package com.ierusalem.employeemanagement.features.edit_profile.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.edit_profile.data.response_model.ResponseModel
import com.ierusalem.employeemanagement.features.edit_profile.domain.EditProfileRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import okhttp3.RequestBody
import retrofit2.Response

class EditProfileRepositoryImpl(val context: Context, private val preferences: PreferenceHelper): EditProfileRepository {
    override suspend fun updateProfile(body: RequestBody): Response<ResponseModel> {
        return RestClient(context = context).editProfileService.updateProfile(
            authToken = preferences.getToken(),
            body = body
        )
    }
}