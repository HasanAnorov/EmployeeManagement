package com.ierusalem.employeemanagement.features.statistics.domain

sealed interface StatisticsScreenNavigation {
    data object Failure: StatisticsScreenNavigation
    data object NavIconClick: StatisticsScreenNavigation
    data object DownloadStatistics: StatisticsScreenNavigation
    data object DownloadPersonalStatistics: StatisticsScreenNavigation
}