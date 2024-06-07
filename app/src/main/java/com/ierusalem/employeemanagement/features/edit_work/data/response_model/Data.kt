package com.ierusalem.employeemanagement.features.edit_work.data.response_model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("file")
    val `file`: List<File>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: Int
)