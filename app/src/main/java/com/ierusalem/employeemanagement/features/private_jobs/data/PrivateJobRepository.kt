package com.ierusalem.employeemanagement.features.private_jobs.data

import com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response.CommandsResponse
import retrofit2.Response

interface PrivateJobRepository {
    suspend fun getMessage(status: String): Response<CommandsResponse>
}