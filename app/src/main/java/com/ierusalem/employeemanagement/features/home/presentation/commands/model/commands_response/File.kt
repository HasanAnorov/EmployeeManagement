package com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response


import com.google.gson.annotations.SerializedName

data class File(
    @SerializedName("file")
    val `file`: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: Int
)