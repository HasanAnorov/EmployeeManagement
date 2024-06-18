package com.ierusalem.employeemanagement.features.for_information.data

import android.content.Context
import com.ierusalem.employeemanagement.core.app.RestClient
import com.ierusalem.employeemanagement.features.for_information.data.model.InformationResponse
import com.ierusalem.employeemanagement.features.for_information.data.model_received.ForInformationReceivedResponse
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import retrofit2.Response

class ForInformationRepositoryImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
): ForInformationRepository {

    override suspend fun deleteInformation(id: String): Response<Unit> {
        return RestClient(context).forInformationService.deleteWorkById(
            authToken = preferenceHelper.getToken(),
            id = id
        )
    }

    override suspend fun getReceivedInformation(id:String): Response<ForInformationReceivedResponse> {
        return RestClient(context).forInformationService.getReceivedInformation(
            authToken = preferenceHelper.getToken(),
            id = id
        )
    }

    override suspend fun getReceivedInformationDesc(id: String): Response<ForInformationReceivedResponse> {
        return RestClient(context).forInformationService.getReceivedInformationDesc(
            authToken = preferenceHelper.getToken(),
            id = id
        )
    }

    override suspend fun getReceivedInformationBadgeCount(status:String): Response<ForInformationReceivedResponse> {
        return RestClient(context).forInformationService.getReceivedBadgeCount(
            authToken = preferenceHelper.getToken(),
            status = status
        )
    }

    override suspend fun getSenInformation(id:String): Response<InformationResponse> {
        return RestClient(context).forInformationService.getSentInformation(
            authToken = preferenceHelper.getToken(),
            id = id
        )
    }

}