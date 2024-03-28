package com.ierusalem.employeemanagement.features.home.presentation.employees.model


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any
)