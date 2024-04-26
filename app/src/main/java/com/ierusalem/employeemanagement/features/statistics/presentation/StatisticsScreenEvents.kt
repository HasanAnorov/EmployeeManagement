package com.ierusalem.employeemanagement.features.statistics.presentation

sealed interface StatisticsScreenEvents {
    data object NavIconClick: StatisticsScreenEvents
    data object DownloadStatistics: StatisticsScreenEvents
}