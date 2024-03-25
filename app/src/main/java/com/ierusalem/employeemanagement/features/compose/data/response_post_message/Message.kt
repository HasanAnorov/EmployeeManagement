package com.ierusalem.employeemanagement.features.compose.data.response_post_message


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("confirm_at")
    val confirmAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_user")
    val createdUser: Int,
    @SerializedName("done")
    val done: Int,
    @SerializedName("failed")
    val failed: Int,
    @SerializedName("file")
    val `file`: List<File>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: String
)