package com.ierusalem.employeemanagement.features.statistics.data

import android.content.Context
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model.StatisticsResponse
import com.ierusalem.employeemanagement.utils.PreferenceHelper
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
}