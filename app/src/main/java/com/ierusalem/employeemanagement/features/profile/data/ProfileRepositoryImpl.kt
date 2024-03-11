package com.ierusalem.employeemanagement.features.profile.data

import android.content.Context
import android.util.Log
import com.ierusalem.employeemanagement.app.RestClient
import com.ierusalem.employeemanagement.features.profile.domain.ProfileRepository
import com.ierusalem.employeemanagement.features.profile.presentation.ProfileScreenData
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class ProfileRepositoryImpl(private val preferences: PreferenceHelper, private val context: Context): ProfileRepository {

    override suspend fun getUser(): Response<ProfileScreenData> {
        Log.d("ahi3646_token", "getUser: ${preferences.getToken()} ")
        return RestClient(context = context).profileService.getUser(authToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzE1MzE4NzE0LCJpYXQiOjE3MTAxMzQ3MTQsImp0aSI6ImI5M2IyYzY5OGQ0OTQ0YTBiZDViY2UyNThiMjQwNmE4IiwidXNlcl9pZCI6M30.M_NoRpvCAwjtKbysDNfzbQraAGJ-yHG-sKpWbiVWQDM")
    }

}