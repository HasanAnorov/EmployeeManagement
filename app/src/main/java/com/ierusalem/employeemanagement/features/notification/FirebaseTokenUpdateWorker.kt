package com.ierusalem.employeemanagement.features.notification

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ierusalem.employeemanagement.features.edit_profile.data.EditProfileRepositoryImpl
import com.ierusalem.employeemanagement.utils.Constants
import com.ierusalem.employeemanagement.utils.PreferenceHelper

class FirebaseTokenUpdateWorker(
    private val applicationContext: Context,
    private val parameters: WorkerParameters
): CoroutineWorker(applicationContext, parameters) {

    override suspend fun doWork(): Result {
        val token = parameters.inputData.getString(Constants.TOKEN_KEY_FOR_WORK_MANAGER)
        val preferenceHelper = PreferenceHelper(applicationContext)
        val editProfileRepoImpl = EditProfileRepositoryImpl(applicationContext,preferenceHelper )
        Log.d("ahi3646", "doWork: $token ")

        return if(token!=null){
            if(editProfileRepoImpl.updateFirebaseToken(token).isSuccessful)
            Result.success() else Result.failure()
        }else{
            Result.failure()
        }

    }


}