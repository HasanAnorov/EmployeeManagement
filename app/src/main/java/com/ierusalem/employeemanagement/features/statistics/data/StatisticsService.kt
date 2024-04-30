package com.ierusalem.employeemanagement.features.statistics.data

import com.ierusalem.employeemanagement.features.statistics.data.model.solo_statistics_response_model.SoloStatisticsResponse
import com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model.StatisticsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StatisticsService {
    @GET("users_statistics")
    suspend fun getStatistics(
        @Header("Authorization") authToken: String,
        @Query("page_size") pageSize: Int
    ): Response<StatisticsResponse>

    @GET("solo_statistics")
    suspend fun getSoloStatistics(
        @Header("Authorization") authToken: String,
        @Query("page_size") pageSize: Int
    ): Response<SoloStatisticsResponse>
}