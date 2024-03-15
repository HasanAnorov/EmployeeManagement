package com.ierusalem.employeemanagement.core

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)