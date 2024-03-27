package com.ierusalem.employeemanagement.features.staff_home.data.model.response_messages


import com.google.gson.annotations.SerializedName

data class ResponseMessages(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)