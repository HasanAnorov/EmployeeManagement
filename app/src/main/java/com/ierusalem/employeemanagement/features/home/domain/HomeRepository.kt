package com.ierusalem.employeemanagement.features.home.domain

import retrofit2.Response

interface HomeRepository {
    suspend fun logoutUser():Response<Unit>

    fun deleteToken()
    fun deleteRefreshToken()
}