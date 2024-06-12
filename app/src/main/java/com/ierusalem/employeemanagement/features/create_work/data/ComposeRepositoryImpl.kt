package com.ierusalem.employeemanagement.features.create_work.data

import android.content.Context
import com.ierusalem.employeemanagement.core.app.RestClient
import com.ierusalem.employeemanagement.features.create_work.data.response_post_message.ResponsePostMessage
import com.ierusalem.employeemanagement.features.create_work.domain.ComposeRepository
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
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