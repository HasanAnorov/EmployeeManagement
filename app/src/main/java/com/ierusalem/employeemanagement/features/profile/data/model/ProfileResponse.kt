package com.ierusalem.employeemanagement.features.profile.data.model


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: User
)