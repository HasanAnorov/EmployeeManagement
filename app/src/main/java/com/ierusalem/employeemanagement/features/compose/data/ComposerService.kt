package com.ierusalem.employeemanagement.features.compose.data

import com.ierusalem.employeemanagement.features.compose.data.response_post_message.ResponsePostMessage
import com.ierusalem.employeemanagement.features.notification.SendMessageDto
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

    @POST("/send")
    suspend fun sendMessage(
        @Header("Authorization") authToken: String,
        @Body body: SendMessageDto
    )

}