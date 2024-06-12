package com.ierusalem.employeemanagement.features.create_work.data.response_post_message


import com.google.gson.annotations.SerializedName

data class ResponsePostMessage(
    @SerializedName("message")
    val message: Message,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)