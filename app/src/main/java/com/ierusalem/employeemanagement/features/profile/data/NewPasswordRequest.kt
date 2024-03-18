package com.ierusalem.employeemanagement.features.profile.data

data class NewPasswordRequest(
    val old_password: String,
    val new_password: String
)