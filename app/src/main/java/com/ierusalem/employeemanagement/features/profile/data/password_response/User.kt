package com.ierusalem.employeemanagement.features.profile.data.password_response


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("complete")
    val complete: Int,
    @SerializedName("date_joined")
    val dateJoined: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("groups")
    val groups: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_staff")
    val isStaff: Boolean,
    @SerializedName("is_superuser")
    val isSuperuser: Boolean,
    @SerializedName("last_login")
    val lastLogin: Any,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_no")
    val phoneNo: String,
    @SerializedName("unvoni")
    val unvoni: String,
    @SerializedName("user_permissions")
    val userPermissions: List<Any>,
    @SerializedName("username")
    val username: String,
    @SerializedName("xonasi")
    val xonasi: String
)