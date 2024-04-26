package com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any
)