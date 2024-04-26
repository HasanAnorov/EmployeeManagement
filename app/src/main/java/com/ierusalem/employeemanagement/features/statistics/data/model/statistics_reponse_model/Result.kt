package com.ierusalem.employeemanagement.features.statistics.data.model.statistics_reponse_model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("bajarildi")
    val bajarildi: Int,
    @SerializedName("bajarilmadi")
    val bajarilmadi: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("kechikibbajarildi")
    val kechikibbajarildi: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_no")
    val phoneNo: String,
    @SerializedName("qabulqildi")
    val qabulqildi: Int,
    @SerializedName("superuser")
    val superuser: Boolean,
    @SerializedName("unvoni")
    val unvoni: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("yuborildi")
    val yuborildi: Int
)