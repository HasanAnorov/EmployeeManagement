package com.ierusalem.employeemanagement.features.personal_statistics.data

import com.ierusalem.employeemanagement.features.personal_statistics.data.models.sent.PersonalStatisticsSent
import com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model.StatisticsResponse
import retrofit2.Response

interface PersonalStatisticsRepository {
    suspend fun getPersonalStatisticsSent(pageSize: Int): Response<PersonalStatisticsSent>
    suspend fun getPersonalStatisticsReceived(pageSize: Int): Response<PersonalStatisticsSent>
}