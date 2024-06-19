package com.ierusalem.employeemanagement.features.for_information_edit.data

import android.content.Context
import com.ierusalem.employeemanagement.core.app.RestClient
import com.ierusalem.employeemanagement.core.utils.PreferenceHelper
import com.ierusalem.employeemanagement.features.for_information_edit.presentation.EditInformationRepository
import okhttp3.RequestBody
import retrofit2.Response

class EditInformationRepositoryImpl(
    val context: Context,
    private val preferences: PreferenceHelper
): EditInformationRepository {

    override suspend fun saveEditedInformation(
        body: RequestBody,
        informationId: String
    ): Response<Unit> {
        return RestClient(context = context).editInformationService.saveEditedInformation(
            authToken = preferences.getToken(),
            body = body,
            informationId = informationId
        )
    }

}