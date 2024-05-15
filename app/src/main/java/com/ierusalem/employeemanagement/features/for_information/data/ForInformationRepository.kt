package com.ierusalem.employeemanagement.features.for_information.data

import com.ierusalem.employeemanagement.features.for_information.data.model.InformationResponse
import com.ierusalem.employeemanagement.features.for_information.data.model_received.ForInformationReceivedResponse
import retrofit2.Response

interface ForInformationRepository {
    suspend fun getSenInformation(id:String): Response<InformationResponse>
    suspend fun getReceivedInformation(id:String): Response<ForInformationReceivedResponse>
    suspend fun getReceivedInformationDesc(id:String): Response<ForInformationReceivedResponse>
    suspend fun getReceivedInformationBadgeCount(status:String): Response<ForInformationReceivedResponse>
}