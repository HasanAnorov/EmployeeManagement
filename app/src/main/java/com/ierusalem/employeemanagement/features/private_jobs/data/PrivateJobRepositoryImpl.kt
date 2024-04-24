package com.ierusalem.employeemanagement.features.private_jobs.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.CommandsResponse
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class PrivateJobRepositoryImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
): PrivateJobRepository {
    override suspend fun getMessage(status: String): Response<CommandsResponse> {
        return RestClient(context).privateJobsService.getCommand(
            authToken = preferenceHelper.getToken(),
            status = status
        )
    }
}