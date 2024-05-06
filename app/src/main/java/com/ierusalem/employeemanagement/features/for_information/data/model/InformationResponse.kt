package com.ierusalem.employeemanagement.features.for_information.data.model


import com.google.gson.annotations.SerializedName

data class InformationResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int
)