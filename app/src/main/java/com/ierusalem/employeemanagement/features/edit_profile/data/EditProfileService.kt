package com.ierusalem.employeemanagement.features.edit_profile.data

import com.ierusalem.employeemanagement.features.edit_profile.data.response_model.ResponseModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface EditProfileService {

    @PUT("put_profil")
    suspend fun updateProfile(
        @Header("Authorization") authToken: String,
        @Body body: RequestBody
    ): Response<ResponseModel>

    @PUT("updateFirebase_token")
    suspend fun updateFirebaseToken(
        @Header("Authorization") authToken: String,
        @Body tokenRequest: TokenRequest
    ): Response<Unit>

}

data class TokenRequest(
    val firebase_token: String
)