package com.ierusalem.employeemanagement.features.work_description.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WorkDescriptionService {

    //fixme ask Nurbek for right api path
    @GET("message_get_id")
    suspend fun getMessageById(
        @Header("Authorization") authToken: String,
        @Query("work_id") workId: String
    ): Response<Int>
}