package com.ierusalem.employeemanagement.features.statistics.domain

sealed interface StatisticsScreenNavigation {
    data object Failure: StatisticsScreenNavigation
}