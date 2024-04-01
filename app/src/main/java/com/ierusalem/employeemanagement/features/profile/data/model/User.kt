package com.ierusalem.employeemanagement.features.profile.data.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_no")
    val phoneNo: String,
    @SerializedName("unvoni")
    val unvoni: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("xonasi")
    val xonasi: String
)