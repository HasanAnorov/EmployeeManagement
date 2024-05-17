package com.ierusalem.employeemanagement.features.personal_statistics.data.models.sent


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("bajarildi")
    val bajarildi: Int,
    @SerializedName("bajarilmadi")
    val bajarilmadi: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kechikibbajarildi")
    val kechikibbajarildi: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("patronymic_name")
    val patronymicName: String,
    @SerializedName("qabulqildi")
    val qabulqildi: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("yuborildi")
    val yuborildi: Int
)