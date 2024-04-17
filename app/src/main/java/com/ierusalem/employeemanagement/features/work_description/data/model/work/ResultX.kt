package com.ierusalem.employeemanagement.features.work_description.data.model.work

import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("adminlast_name")
    val adminlastName: String,
    @SerializedName("adminunvoni")
    val adminunvoni: String,
    @SerializedName("adminusername")
    val adminusername: String,
    @SerializedName("created_user")
    val createdUser: Int,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("file")
    val `file`: List<FileX>,
    @SerializedName("file_employee")
    val fileEmployee: List<FileEmployee>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("text_employee")
    val textEmployee: String,
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