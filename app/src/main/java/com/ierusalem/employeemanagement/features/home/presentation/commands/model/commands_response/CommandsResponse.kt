package com.ierusalem.employeemanagement.features.home.presentation.commands.model.commands_response


import com.google.gson.annotations.SerializedName

data class CommandsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)