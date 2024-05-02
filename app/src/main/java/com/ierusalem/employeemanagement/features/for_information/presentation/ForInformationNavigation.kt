package com.ierusalem.employeemanagement.features.for_information.presentation

sealed interface ForInformationNavigation {
    data object ArrowBackClick: ForInformationNavigation
    data object InvalidResponse: ForInformationNavigation
}