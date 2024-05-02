package com.ierusalem.employeemanagement.features.for_information.data

import retrofit2.Response

interface ForInformationRepository {
    suspend fun getSenInformation(): Response<Unit>
    suspend fun getReceivedInformation(): Response<Unit>
}