package com.ierusalem.employeemanagement.features.work_description.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.work_description.data.model.response.MarkAsDoneResponse
import com.ierusalem.employeemanagement.features.work_description.data.model.work.WorkItem
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class WorkDescriptionRepositoryImpl(
    private val context: Context,
    private val preferenceHelper: PreferenceHelper
): WorkDescriptionRepository {
    override suspend fun getMessageById(workId: String): Response<WorkItem> {
        return RestClient(context).workDescriptionService.getMessageById(
            authToken = preferenceHelper.getToken(),
            workId = workId
        )
    }

    override suspend fun markAsDone(workId: String): Response<MarkAsDoneResponse> {
        return RestClient(context).workDescriptionService.markAsDone(
            authToken = preferenceHelper.getToken(),
            workId = workId
        )
    }
}