package com.ierusalem.employeemanagement.features.profile.data.password_response


import com.google.gson.annotations.SerializedName

data class PasswordResponse(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: User
)