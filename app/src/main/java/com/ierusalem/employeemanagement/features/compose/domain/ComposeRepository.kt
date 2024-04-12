package com.ierusalem.employeemanagement.features.compose.domain

import com.ierusalem.employeemanagement.features.compose.data.response_post_message.ResponsePostMessage
import com.ierusalem.employeemanagement.features.notification.SendMessageDto
import okhttp3.RequestBody
import retrofit2.Response

interface ComposeRepository {
    suspend fun postMessage(body: RequestBody): Response<ResponsePostMessage>
    suspend fun send(body: SendMessageDto)
}