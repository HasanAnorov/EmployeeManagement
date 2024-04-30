package com.ierusalem.employeemanagement.features.statistics.data.model.solo_statistics_response_model


import com.google.gson.annotations.SerializedName

data class SoloStatisticsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int
)