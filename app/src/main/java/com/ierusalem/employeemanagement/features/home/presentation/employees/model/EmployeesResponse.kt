package com.ierusalem.employeemanagement.features.home.presentation.employees.model


import com.google.gson.annotations.SerializedName

data class EmployeesResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)