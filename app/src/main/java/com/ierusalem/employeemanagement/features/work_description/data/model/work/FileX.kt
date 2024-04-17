package com.ierusalem.employeemanagement.features.work_description.data.model.work


import com.google.gson.annotations.SerializedName

data class FileX(
    @SerializedName("file")
    val `file`: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: Int
)