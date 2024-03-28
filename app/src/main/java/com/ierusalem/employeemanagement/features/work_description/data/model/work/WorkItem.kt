package com.ierusalem.employeemanagement.features.work_description.data.model.work


import com.google.gson.annotations.SerializedName

data class WorkItem(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)