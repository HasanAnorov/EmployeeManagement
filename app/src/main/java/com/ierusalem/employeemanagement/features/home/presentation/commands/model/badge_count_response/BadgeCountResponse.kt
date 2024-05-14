package com.ierusalem.employeemanagement.features.home.presentation.commands.model.badge_count_response


import com.google.gson.annotations.SerializedName

data class BadgeCountResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("results")
    val results: List<ResultBadgeCount>,
    @SerializedName("total_pages")
    val totalPages: Int
)