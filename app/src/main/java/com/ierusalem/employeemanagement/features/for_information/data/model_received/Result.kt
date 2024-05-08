package com.ierusalem.employeemanagement.features.for_information.data.model_received


import com.google.gson.annotations.SerializedName
import com.ierusalem.employeemanagement.features.work_description.data.model.work.FileX

data class Result(
    @SerializedName("admin_image")
    val adminImage: String,
    @SerializedName("adminlast_name")
    val adminlastName: String,
    @SerializedName("adminunvoni")
    val adminunvoni: String?,
    @SerializedName("adminusername")
    val adminusername: String,
    @SerializedName("created_user")
    val createdUser: Int,
    @SerializedName("file")
    val `file`: List<FileX>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("patronymic_name")
    val patronymicName: String?,
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