package com.ierusalem.employeemanagement.features.home.presentation.commands.model.badge_count_response


import com.google.gson.annotations.SerializedName

data class ResultBadgeCount(
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
    val `file`: List<Any>,
    @SerializedName("file_employee")
    val fileEmployee: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("img_user")
    val imgUser: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("patronymic_name")
    val patronymicName: Any,
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