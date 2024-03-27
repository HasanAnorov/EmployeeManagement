package com.ierusalem.employeemanagement.features.staff_home.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
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
}