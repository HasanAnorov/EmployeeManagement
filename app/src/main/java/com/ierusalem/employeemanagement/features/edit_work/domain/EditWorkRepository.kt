package com.ierusalem.employeemanagement.features.edit_work.domain

import retrofit2.Response

interface EditWorkRepository {
    suspend fun deleteWorkById(id: String): Response<Unit>
}