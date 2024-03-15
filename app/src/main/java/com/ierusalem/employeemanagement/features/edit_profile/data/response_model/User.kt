package com.ierusalem.employeemanagement.features.edit_profile.data.response_model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("complete")
    val complete: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_no")
    val phoneNo: String,
    @SerializedName("unvoni")
    val unvoni: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("xonasi")
    val xonasi: String
)