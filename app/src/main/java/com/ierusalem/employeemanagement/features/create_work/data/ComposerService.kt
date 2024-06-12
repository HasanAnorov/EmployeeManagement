package com.ierusalem.employeemanagement.features.create_work.data

import com.ierusalem.employeemanagement.features.create_work.data.response_post_message.ResponsePostMessage
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ComposerService {

    @POST("post_message")
    suspend fun composeCommand(
        @Header("Authorization") authToken: String,
        @Body body: RequestBody
    ): Response<ResponsePostMessage>

    @POST("post_malumotuchun")
    suspend fun composeInformation(
        @Header("Authorization") authToken: String,
        @Body body: RequestBody
    ): Response<ResponsePostMessage>

}