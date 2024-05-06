package com.ierusalem.employeemanagement.features.for_information.data

import com.ierusalem.employeemanagement.features.for_information.data.model.InformationResponse
import com.ierusalem.employeemanagement.features.for_information.data.model_received.ForInformationReceivedResponse
import retrofit2.Response

interface ForInformationRepository {
    suspend fun getSenInformation(): Response<InformationResponse>
    suspend fun getReceivedInformation(): Response<ForInformationReceivedResponse>
}