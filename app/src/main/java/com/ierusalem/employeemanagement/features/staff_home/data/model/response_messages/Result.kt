package com.ierusalem.employeemanagement.features.staff_home.data.model.response_messages


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val workId: Int,
    @SerializedName("created_user")
    val createdUser: Int,
    @SerializedName("file")
    val `file`: List<Any>,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_unvoni")
    val userUnvoni: String,
    @SerializedName("user_xonasi")
    val userXonasi: String
)