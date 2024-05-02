package com.ierusalem.employeemanagement.features.for_information.data

import com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model.StatisticsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ForInformationService {

    @GET("malumotuchun")
    suspend fun getReceivedInformation(
        @Header("Authorization") authToken: String
    ): Response<StatisticsResponse>

    @GET("get_malumotuchun")
    suspend fun getSentInformation(
        @Header("Authorization") authToken: String
    ): Response<StatisticsResponse>

}