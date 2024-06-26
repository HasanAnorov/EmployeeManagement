package com.ierusalem.employeemanagement.features.staff_home.domain

import com.ierusalem.employeemanagement.features.staff_home.data.model.response_messages.ResponseMessages
import retrofit2.Response

interface StaffHomeRepository {
    suspend fun getUserMessages(status: String):Response<ResponseMessages>
    suspend fun logoutUser():Response<Unit>
    fun deleteToken()
    fun deleteRefreshToken()
}