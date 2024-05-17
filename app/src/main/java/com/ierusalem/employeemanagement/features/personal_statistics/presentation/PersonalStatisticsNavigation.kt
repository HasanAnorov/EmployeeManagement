package com.ierusalem.employeemanagement.features.personal_statistics.presentation

import com.ierusalem.employeemanagement.features.statistics.domain.StatisticsScreenNavigation

sealed interface PersonalStatisticsNavigation {

    data object Failure : PersonalStatisticsNavigation
    data object OnNavIconClick : PersonalStatisticsNavigation
    data object DownloadPersonalStatisticsSent: PersonalStatisticsNavigation
    data object DownloadPersonalStatisticsReceived: PersonalStatisticsNavigation

}