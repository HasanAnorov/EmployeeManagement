package com.ierusalem.employeemanagement.features.compose.presentation

interface ComposeScreenNavigation {
    data object InvalidResponse: ComposeScreenNavigation
    data object Success: ComposeScreenNavigation
}