package com.ierusalem.employeemanagement.features.work_description.domain

import retrofit2.Response

interface WorkDescriptionRepository {
    suspend fun getMessageById(workId: String): Response<Int>
}