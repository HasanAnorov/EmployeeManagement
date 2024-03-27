package com.ierusalem.employeemanagement.features.work_description.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class WorkDescriptionRepositoryImpl(
    private val context: Context,
    private val preferenceHelper: PreferenceHelper
): WorkDescriptionRepository {
    override suspend fun getMessageById(workId: String): Response<Int> {
        return RestClient(context).workDescriptionService.getMessageById(
            authToken = preferenceHelper.getToken(),
            workId = workId
        )
    }
}