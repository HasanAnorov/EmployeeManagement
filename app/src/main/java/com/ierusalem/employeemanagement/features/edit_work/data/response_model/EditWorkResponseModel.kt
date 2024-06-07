package com.ierusalem.employeemanagement.features.edit_work.data.response_model


import com.google.gson.annotations.SerializedName

data class EditWorkResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
)