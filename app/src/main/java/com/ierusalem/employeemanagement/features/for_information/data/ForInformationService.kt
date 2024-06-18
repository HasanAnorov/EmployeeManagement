package com.ierusalem.employeemanagement.features.for_information.data

import com.ierusalem.employeemanagement.features.for_information.data.model.InformationResponse
import com.ierusalem.employeemanagement.features.for_information.data.model_received.ForInformationReceivedResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ForInformationService {

    @GET("malumotuchun")
    suspend fun getReceivedInformation(
        @Header("Authorization") authToken: String,
        @Query("id") id:String
    ): Response<ForInformationReceivedResponse>

    @DELETE("update_malumotuchun/")
    suspend fun deleteWorkById(
        @Header("Authorization") authToken: String,
        @Query("id") id:String,
    ): Response<Unit>

    @GET("malumotuchun")
    suspend fun getReceivedBadgeCount(
        @Header("Authorization") authToken: String,
        @Query("status") status:String
    ): Response<ForInformationReceivedResponse>

    @GET("get_malumotuchun")
    suspend fun getSentInformation(
        @Header("Authorization") authToken: String,
        @Query("id") id:String
    ): Response<InformationResponse>

    @GET("malumot_id")
    suspend fun getReceivedInformationDesc(
        @Header("Authorization") authToken: String,
        @Query("id") id:String
    ): Response<ForInformationReceivedResponse>
}