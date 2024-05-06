package com.ierusalem.employeemanagement.features.for_information.data.model_received


import com.google.gson.annotations.SerializedName

data class ForInformationReceivedResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int
)