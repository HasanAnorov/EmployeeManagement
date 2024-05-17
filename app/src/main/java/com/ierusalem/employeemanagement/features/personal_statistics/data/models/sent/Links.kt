package com.ierusalem.employeemanagement.features.personal_statistics.data.models.sent


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any
)