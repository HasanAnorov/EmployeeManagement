package com.ierusalem.employeemanagement.features.home.presentation.commands.model.badge_count_response


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any
)