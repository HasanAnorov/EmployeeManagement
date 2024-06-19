package com.ierusalem.employeemanagement.features.for_information_edit.data

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface EditInformationService {
    @PUT("update_malumotuchun/")
    suspend fun saveEditedInformation(
        @Header("Authorization") authToken: String,
        @Body body: RequestBody,
        @Query("id") informationId:String,
    ): Response<Unit>
}