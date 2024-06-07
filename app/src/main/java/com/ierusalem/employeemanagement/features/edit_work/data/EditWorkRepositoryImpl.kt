package com.ierusalem.employeemanagement.features.edit_work.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.edit_work.data.response_model.EditWorkResponseModel
import com.ierusalem.employeemanagement.features.edit_work.domain.EditWorkRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import okhttp3.RequestBody
import retrofit2.Response

class EditWorkRepositoryImpl(val context: Context, private val preferences: PreferenceHelper) :
    EditWorkRepository {
    override suspend fun deleteWorkById(id: String): Response<Unit> {
        return RestClient(context = context).editWorkService.deleteWorkById(
            authToken = preferences.getToken(),
            workId = id
        )
    }

    override suspend fun saveEditedWork(body: RequestBody, workId: String): Response<EditWorkResponseModel> {
        return RestClient(context = context).editWorkService.saveEditedWork(
            authToken = preferences.getToken(),
            body = body,
            workId = workId
        )
    }
}