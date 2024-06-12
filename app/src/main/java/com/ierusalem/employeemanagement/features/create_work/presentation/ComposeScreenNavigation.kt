package com.ierusalem.employeemanagement.features.create_work.presentation

interface ComposeScreenNavigation {
    data object InvalidResponse: ComposeScreenNavigation
    data object Success: ComposeScreenNavigation
}