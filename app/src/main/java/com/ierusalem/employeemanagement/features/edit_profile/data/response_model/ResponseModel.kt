package com.ierusalem.employeemanagement.features.edit_profile.data.response_model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user")
    val user: User
)