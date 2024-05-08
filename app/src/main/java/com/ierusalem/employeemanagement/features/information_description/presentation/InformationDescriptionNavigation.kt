package com.ierusalem.employeemanagement.features.information_description.presentation

sealed interface InformationDescriptionNavigation {
    data object Failure : InformationDescriptionNavigation
}