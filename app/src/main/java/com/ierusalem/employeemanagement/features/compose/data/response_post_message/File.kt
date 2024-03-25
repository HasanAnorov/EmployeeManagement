package com.ierusalem.employeemanagement.features.compose.data.response_post_message


import com.google.gson.annotations.SerializedName

data class File(
    @SerializedName("file")
    val `file`: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: Int
)