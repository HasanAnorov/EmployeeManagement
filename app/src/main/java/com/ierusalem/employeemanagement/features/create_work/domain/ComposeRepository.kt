package com.ierusalem.employeemanagement.features.create_work.domain

import com.ierusalem.employeemanagement.features.create_work.data.response_post_message.ResponsePostMessage
import okhttp3.RequestBody
import retrofit2.Response

interface ComposeRepository {
    suspend fun postMessage(body: RequestBody): Response<ResponsePostMessage>
    suspend fun postInformation(body: RequestBody): Response<ResponsePostMessage>
}