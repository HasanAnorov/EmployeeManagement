package com.ierusalem.employeemanagement.features.auth.data.entity.auth_request

data class UserLoginRequest(
    val email: String,
    val password: String
)