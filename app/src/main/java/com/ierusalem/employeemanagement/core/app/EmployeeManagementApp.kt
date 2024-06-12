package com.ierusalem.employeemanagement.core.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.ierusalem.employeemanagement.features.auth.di.authModel
import com.ierusalem.employeemanagement.features.create_work.di.composeModule
import com.ierusalem.employeemanagement.features.edit_profile.di.editProfileViewModule
import com.ierusalem.employeemanagement.features.edit_work.di.editWorkModule
import com.ierusalem.employeemanagement.features.for_information.di.forInformationModule
import com.ierusalem.employeemanagement.features.home.di.homeModule
import com.ierusalem.employeemanagement.features.information_description.di.informationDescriptionModule
import com.ierusalem.employeemanagement.features.personal_statistics.di.personalStatisticsModule
import com.ierusalem.employeemanagement.features.private_jobs.di.privateJobsModule
import com.ierusalem.employeemanagement.features.profile.di.profileModule
import com.ierusalem.employeemanagement.features.staff_for_information.di.staffInformationModule
import com.ierusalem.employeemanagement.features.staff_home.di.staffHomeScreenModule
import com.ierusalem.employeemanagement.features.statistics.di.statisticsModule
import com.ierusalem.employeemanagement.features.work_description.di.workDescriptionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EmployeeManagementApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        startKoin {
            androidLogger()
            androidContext(this@EmployeeManagementApp)
            modules(
                authModel,
                statisticsModule,
                homeModule,
                forInformationModule,
                staffInformationModule,
                privateJobsModule,
                personalStatisticsModule,
                informationDescriptionModule,
                profileModule,
                editProfileViewModule,
                editWorkModule,
                composeModule,
                staffHomeScreenModule,
                workDescriptionModule
            )
        }
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "Channel name"
    }

}