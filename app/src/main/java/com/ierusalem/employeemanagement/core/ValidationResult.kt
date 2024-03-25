package com.ierusalem.employeemanagement.core

import com.ierusalem.employeemanagement.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)