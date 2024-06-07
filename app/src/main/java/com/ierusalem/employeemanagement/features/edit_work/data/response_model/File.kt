package com.ierusalem.employeemanagement.features.edit_work.data.response_model


import com.google.gson.annotations.SerializedName

data class File(
    @SerializedName("file")
    val `file`: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("malumotuchun")
    val malumotuchun: Any,
    @SerializedName("message")
    val message: Int
)