package com.ierusalem.employeemanagement.features.for_information_edit.domain

sealed interface ForInformationEditNavigation {
    data object Failure: ForInformationEditNavigation
}