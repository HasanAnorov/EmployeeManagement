package com.ierusalem.employeemanagement.features.statistics.data

import com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model.StatisticsResponse
import retrofit2.Response

interface StatisticsRepository {
    suspend fun getStatistics(pageSize: Int): Response<StatisticsResponse>
}