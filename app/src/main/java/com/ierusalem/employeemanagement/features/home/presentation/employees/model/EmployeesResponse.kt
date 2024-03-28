package com.ierusalem.employeemanagement.features.home.presentation.employees.model


import com.google.gson.annotations.SerializedName

data class EmployeesResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("links")
    val links: Links,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int
)