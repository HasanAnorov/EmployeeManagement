package com.ierusalem.employeemanagement.features.edit_work.data

import com.ierusalem.employeemanagement.features.edit_work.data.response_model.EditWorkResponseModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface EditWorkService {

    @DELETE("update_message/")
    suspend fun deleteWorkById(
        @Header("Authorization") authToken: String,
        @Query("id") workId:String,
    ): Response<Unit>

    @PUT("update_message/")
    suspend fun saveEditedWork(
        @Header("Authorization") authToken: String,
        @Body body: RequestBody,
        @Query("id") workId:String,
    ): Response<EditWorkResponseModel>

}