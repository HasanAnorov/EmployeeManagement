package com.ierusalem.employeemanagement.features.for_information.data.model


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any
)