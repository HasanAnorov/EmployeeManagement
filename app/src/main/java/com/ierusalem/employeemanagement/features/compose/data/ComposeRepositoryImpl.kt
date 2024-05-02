package com.ierusalem.employeemanagement.features.compose.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.compose.data.response_post_message.ResponsePostMessage
import com.ierusalem.employeemanagement.features.compose.domain.ComposeRepository
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import okhttp3.RequestBody
import retrofit2.Response

class ComposeRepositoryImpl(
    private val context: Context,
    private val preferences: PreferenceHelper
): ComposeRepository {
    override suspend fun postMessage(body: RequestBody): Response<ResponsePostMessage> {
        return RestClient(context).composeService.composeCommand(
            authToken = preferences.getToken(),
            body = body
        )
    }

    override suspend fun postInformation(body: RequestBody): Response<ResponsePostMessage> {
        return RestClient(context).composeService.composeInformation(
            authToken = preferences.getToken(),
            body = body
        )
    }

}