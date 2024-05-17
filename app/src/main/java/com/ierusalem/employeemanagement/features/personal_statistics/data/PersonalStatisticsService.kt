package com.ierusalem.employeemanagement.features.personal_statistics.data

import com.ierusalem.employeemanagement.features.personal_statistics.data.models.sent.PersonalStatisticsSent
import com.ierusalem.employeemanagement.features.statistics.data.model.solo_statistics_response_model.SoloStatisticsResponse
import com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model.StatisticsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PersonalStatisticsService {

    @GET("solo_statistics")
    suspend fun getPersonalStatisticsSent(
        @Header("Authorization") authToken: String,
        @Query("page_size") pageSize: Int
    ): Response<PersonalStatisticsSent>

    @GET("solo_statistics2")
    suspend fun getPersonalStatisticsReceived(
        @Header("Authorization") authToken: String,
        @Query("page_size") pageSize: Int
    ): Response<PersonalStatisticsSent>

}