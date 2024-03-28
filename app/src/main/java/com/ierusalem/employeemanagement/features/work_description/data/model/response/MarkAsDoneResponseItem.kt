package com.ierusalem.employeemanagement.features.work_description.data.model.response


import com.google.gson.annotations.SerializedName

data class MarkAsDoneResponseItem(
    @SerializedName("created_user")
    val createdUser: Int,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("file")
    val `file`: List<File>,
    @SerializedName("id")
    val id: Int,
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