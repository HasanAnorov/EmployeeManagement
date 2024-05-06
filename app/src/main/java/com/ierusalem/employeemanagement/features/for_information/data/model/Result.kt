package com.ierusalem.employeemanagement.features.for_information.data.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adminlast_name")
    val adminlastName: String?,
    @SerializedName("admin_image")
    val adminImage: String?,
    @SerializedName("adminunvoni")
    val adminunvoni: String?,
    @SerializedName("adminusername")
    val adminusername: String?,
    @SerializedName("created_user")
    val createdUser: Int,
    @SerializedName("file")
    val `file`: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val image: String,
    @SerializedName("patronymic_name")
    val patronymicName: String?,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("last_name")
    val userLastname: String,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_unvoni")
    val userUnvoni: String?,
    @SerializedName("user_xonasi")
    val userXonasi: String
)