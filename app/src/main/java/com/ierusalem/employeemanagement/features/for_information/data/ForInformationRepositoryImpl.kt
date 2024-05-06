package com.ierusalem.employeemanagement.features.for_information.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.for_information.data.model.InformationResponse
import com.ierusalem.employeemanagement.features.for_information.data.model_received.ForInformationReceivedResponse
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class ForInformationRepositoryImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
): ForInformationRepository {

    override suspend fun getReceivedInformation(): Response<ForInformationReceivedResponse> {
        return RestClient(context).forInformationService.getReceivedInformation(
            authToken = preferenceHelper.getToken()
        )
    }

    override suspend fun getSenInformation(): Response<InformationResponse> {
        return RestClient(context).forInformationService.getSentInformation(
            authToken = preferenceHelper.getToken()
        )
    }

}