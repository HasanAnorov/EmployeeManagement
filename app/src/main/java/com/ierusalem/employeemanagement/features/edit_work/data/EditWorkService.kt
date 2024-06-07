package com.ierusalem.employeemanagement.features.edit_work.data

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Query

interface EditWorkService {

    @DELETE("update_message")
    suspend fun deleteWorkById(
        @Header("Authorization") authToken: String,
        @Query("id") workId:String,
    ): Response<Unit>

}