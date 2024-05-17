package com.ierusalem.employeemanagement.features.personal_statistics.data.models.sent


import com.google.gson.annotations.SerializedName

data class PersonalStatisticsSent(
    @SerializedName("count")
    val count: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int
)