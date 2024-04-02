package com.ierusalem.employeemanagement.features.work_description.domain

import com.ierusalem.employeemanagement.features.work_description.data.model.response.MarkAsDoneResponse
import com.ierusalem.employeemanagement.features.work_description.data.model.work.WorkItem
import retrofit2.Response

interface WorkDescriptionRepository {
    suspend fun getMessageById(workId: String): Response<WorkItem>
    suspend fun getMessageByIdAdmin(workId: String): Response<WorkItem>
    suspend fun markAsDone(workId: String): Response<MarkAsDoneResponse>
}