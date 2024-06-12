package com.ierusalem.employeemanagement.core.app

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.ierusalem.employeemanagement.features.auth.data.AuthorizationService
import com.ierusalem.employeemanagement.features.create_work.data.ComposerService
import com.ierusalem.employeemanagement.features.edit_profile.data.EditProfileService
import com.ierusalem.employeemanagement.features.edit_work.data.EditWorkService
import com.ierusalem.employeemanagement.features.for_information.data.ForInformationService
import com.ierusalem.employeemanagement.features.home.data.HomeService
import com.ierusalem.employeemanagement.features.personal_statistics.data.PersonalStatisticsService
import com.ierusalem.employeemanagement.features.private_jobs.data.PrivateJobService
import com.ierusalem.employeemanagement.features.profile.data.ProfileService
import com.ierusalem.employeemanagement.features.staff_home.data.StaffHomeService
import com.ierusalem.employeemanagement.features.statistics.data.StatisticsService
import com.ierusalem.employeemanagement.features.work_description.data.WorkDescriptionService
import com.ierusalem.employeemanagement.core.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient(context: Context) {

    private val retrofit by lazy {

        val client = OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(25000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val authService: AuthorizationService by lazy {
        retrofit.create(AuthorizationService::class.java)
    }

    val homeService: HomeService by lazy {
        retrofit.create(HomeService::class.java)
    }

    val privateJobsService: PrivateJobService by lazy {
        retrofit.create(PrivateJobService::class.java)
    }

    val statisticsService: StatisticsService by lazy {
        retrofit.create(StatisticsService::class.java)
    }

    val personalStatisticsService: PersonalStatisticsService by lazy {
        retrofit.create(PersonalStatisticsService::class.java)
    }

    val workDescriptionService: WorkDescriptionService by lazy {
        retrofit.create(WorkDescriptionService::class.java)
    }

    val staffHomeService: StaffHomeService by lazy {
        retrofit.create(StaffHomeService::class.java)
    }

    val composeService: ComposerService by lazy {
        retrofit.create(ComposerService::class.java)
    }

    val profileService: ProfileService by lazy {
        retrofit.create(ProfileService::class.java)
    }

    val editProfileService: EditProfileService by lazy {
        retrofit.create(EditProfileService::class.java)
    }

    val editWorkService: EditWorkService by lazy {
        retrofit.create(EditWorkService::class.java)
    }

    val forInformationService: ForInformationService by lazy {
        retrofit.create(ForInformationService::class.java)
    }

}
