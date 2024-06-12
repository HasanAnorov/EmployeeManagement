package com.ierusalem.employeemanagement.features.personal_statistics.data

import android.content.Context
import com.ierusalem.employeemanagement.core.app.RestClient
import com.ierusalem.employeemanagement.features.personal_statistics.data.models.sent.PersonalStatisticsSent
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import retrofit2.Response

class PersonalStatisticsImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
) : PersonalStatisticsRepository {

    override suspend fun getPersonalStatisticsReceived(pageSize: Int): Response<PersonalStatisticsSent> {
        return RestClient(context).personalStatisticsService.getPersonalStatisticsReceived(
            authToken = preferenceHelper.getToken(),
            pageSize = pageSize
        )
    }

    override suspend fun getPersonalStatisticsSent(pageSize: Int): Response<PersonalStatisticsSent> {
        return RestClient(context).personalStatisticsService.getPersonalStatisticsSent(
            authToken = preferenceHelper.getToken(),
            pageSize = pageSize
        )
    }

}