package com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model


import com.google.gson.annotations.SerializedName

data class StatisticsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int
)