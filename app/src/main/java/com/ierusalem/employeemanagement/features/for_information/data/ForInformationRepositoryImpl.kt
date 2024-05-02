package com.ierusalem.employeemanagement.features.for_information.data

import android.content.Context
import com.ierusalem.employeemanagement.utils.PreferenceHelper
import retrofit2.Response

class ForInformationRepositoryImpl(
    private val preferenceHelper: PreferenceHelper,
    val context: Context
): ForInformationRepository {

    override suspend fun getReceivedInformation(): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getSenInformation(): Response<Unit> {
        TODO("Not yet implemented")
    }

}