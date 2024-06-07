package com.ierusalem.employeemanagement.features.edit_work.domain

import com.ierusalem.employeemanagement.features.edit_work.data.response_model.EditWorkResponseModel
import okhttp3.RequestBody
import retrofit2.Response

interface EditWorkRepository {
    suspend fun deleteWorkById(id: String): Response<Unit>
    suspend fun saveEditedWork(body: RequestBody, workId: String): Response<EditWorkResponseModel>
}