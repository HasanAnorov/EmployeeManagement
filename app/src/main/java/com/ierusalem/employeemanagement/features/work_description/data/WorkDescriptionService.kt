package com.ierusalem.employeemanagement.features.work_description.data

import com.ierusalem.employeemanagement.features.work_description.data.model.response.MarkAsDoneResponse
import com.ierusalem.employeemanagement.features.work_description.data.model.work.WorkItem
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface WorkDescriptionService {

    @GET("message")
    suspend fun getMessageById(
        @Header("Authorization") authToken: String,
        @Query("id") workId: String
    ): Response<WorkItem>

    @GET("admin_message")
    suspend fun getMessageByIdAdmin(
        @Header("Authorization") authToken: String,
        @Query("id") workId: String
    ): Response<WorkItem>


    @POST("messagepost/")
    suspend fun markAsDone(
        @Header("Authorization") authToken: String,
        @Query("id") workId : String,
        @Body body: RequestBody
    ): Response<MarkAsDoneResponse>
}
