package com.ierusalem.employeemanagement.features.auth.data.entity.auth_response


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_no")
    val phoneNo: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("is_staff")
    val isStaff: Boolean,

    @SerializedName("superuser")
    val isSuperUser: Boolean
)