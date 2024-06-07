package com.ierusalem.employeemanagement.features.edit_work.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.edit_work.domain.EditWorkRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class EditWorkRepositoryImpl(val context: Context, private val preferences: PreferenceHelper): EditWorkRepository {
    override suspend fun deleteWorkById(id: String): Response<Unit> {
        return RestClient(context = context).editWorkService.deleteWorkById(
            authToken = preferences.getToken(),
            workId = id
        )
    }
}