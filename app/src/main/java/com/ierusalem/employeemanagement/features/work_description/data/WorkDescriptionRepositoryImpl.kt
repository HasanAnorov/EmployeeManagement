package com.ierusalem.employeemanagement.features.work_description.data

import android.content.Context
import android.util.Log
import com.ierusalem.employeemanagement.core.app.RestClient
import com.ierusalem.employeemanagement.features.work_description.data.model.response.MarkAsDoneResponse
import com.ierusalem.employeemanagement.features.work_description.data.model.work.WorkItem
import com.ierusalem.employeemanagement.features.work_description.domain.WorkDescriptionRepository
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import okhttp3.RequestBody
import retrofit2.Response

class WorkDescriptionRepositoryImpl(
    private val context: Context,
    private val preferenceHelper: PreferenceHelper
): WorkDescriptionRepository {
    override suspend fun deleteWorkById(workId: String): Response<Unit> {
        return RestClient(context).workDescriptionService.deleteWorkById(
            authToken = preferenceHelper.getToken(),
            workId = workId
        )
    }

    override suspend fun letEditWorkById(workId: String): Response<Unit> {
        return RestClient(context).workDescriptionService.letEditeWorkById(
            authToken = preferenceHelper.getToken(),
            workId = workId
        )
    }

    override suspend fun getMessageById(workId: String): Response<WorkItem> {
        Log.d("ahi3646", "getMessageById: ")
        return RestClient(context).workDescriptionService.getMessageById(
            authToken = preferenceHelper.getToken(),
            workId = workId
        )
    }

    override suspend fun getMessageByIdAdmin(workId: String): Response<WorkItem> {
        Log.d("ahi3646", "getMessageByIdAdmin: ")
        return RestClient(context).workDescriptionService.getMessageByIdAdmin(
            authToken = preferenceHelper.getToken(),
            workId = workId
        )
    }

    override suspend fun markAsDone(workId: String, body: RequestBody): Response<MarkAsDoneResponse> {
        return RestClient(context).workDescriptionService.markAsDone(
            authToken = preferenceHelper.getToken(),
            workId = workId,
            body = body
        )
    }
}