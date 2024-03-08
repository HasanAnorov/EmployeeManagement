package com.ierusalem.employeemanagement.features.auth.data.entity.auth_response


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("user")
    val user: User
)