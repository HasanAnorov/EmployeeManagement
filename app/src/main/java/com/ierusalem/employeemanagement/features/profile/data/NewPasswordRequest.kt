package com.ierusalem.employeemanagement.features.profile.data

data class NewPasswordRequest(
    val oldPassword: String,
    val newPassword: String
)