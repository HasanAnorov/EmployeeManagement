package com.ierusalem.employeemanagement.features.personal_statistics.presentation

sealed interface PersonalStatisticsEvents {
    data object OnNavIconClick: PersonalStatisticsEvents
    data class TabItemClick(val tabIndex: Int): PersonalStatisticsEvents
    data object DownloadPersonalStatisticsSent: PersonalStatisticsEvents
    data object DownloadPersonalStatisticsReceived: PersonalStatisticsEvents
}