package com.ierusalem.employeemanagement.features.for_information_edit.presentation

import okhttp3.RequestBody
import retrofit2.Response

interface EditInformationRepository {
    suspend fun saveEditedInformation(body: RequestBody, informationId: String): Response<Unit>
}