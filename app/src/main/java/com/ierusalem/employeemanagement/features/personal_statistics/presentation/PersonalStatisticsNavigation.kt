package com.ierusalem.employeemanagement.features.personal_statistics.presentation

sealed interface PersonalStatisticsNavigation {

    data object Failure : PersonalStatisticsNavigation
    data object OnNavIconClick : PersonalStatisticsNavigation

}