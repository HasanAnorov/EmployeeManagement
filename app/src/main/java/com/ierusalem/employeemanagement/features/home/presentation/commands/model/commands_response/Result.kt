package com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val workId: Int,
    @SerializedName("created_user")
    val createdUser: Int,
    @SerializedName("file")
    val `file`: List<File>,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("last_name")
    val userLastName: String,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("img_user")
    val userImage: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_unvoni")
    val userUnvoni: String?,
    @SerializedName("user_xonasi")
    val userXonasi: String?,
    @SerializedName("adminusername")
    val adminUsername: String,
    @SerializedName("adminlast_name")
    val adminLastname: String,
    @SerializedName("image")
    val adminImage: String,
    @SerializedName("adminunvoni")
    val adminPosition: String?,
    @SerializedName("end_time")
    val endTime: String
)