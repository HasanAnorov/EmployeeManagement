package com.ierusalem.employeemanagement.features.statistics.data

import android.content.Context
import com.ierusalem.employeemanagement.core.app.RestClient
import com.ierusalem.employeemanagement.features.statistics.data.model.solo_statistics_response_model.SoloStatisticsResponse
import com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model.StatisticsResponse
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import retrofit2.Response

class StatisticsRepositoryImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
): StatisticsRepository {
    override suspend fun getStatistics(pageSize: Int): Response<StatisticsResponse> {
        return RestClient(context).statisticsService.getStatistics(
            authToken = preferenceHelper.getToken(),
            pageSize = pageSize
        )
    }

    override suspend fun getSoloStatistics(pageSize: Int): Response<SoloStatisticsResponse> {
        return RestClient(context).statisticsService.getSoloStatistics(
            authToken = preferenceHelper.getToken(),
            pageSize = pageSize
        )
    }
}