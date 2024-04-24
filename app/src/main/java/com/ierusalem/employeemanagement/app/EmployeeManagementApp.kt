package com.ierusalem.employeemanagement.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.ierusalem.employeemanagement.features.auth.di.authModel
import com.ierusalem.employeemanagement.features.compose.di.composeModule
import com.ierusalem.employeemanagement.features.edit_profile.di.editProfileViewModule
import com.ierusalem.employeemanagement.features.home.di.homeModule
import com.ierusalem.employeemanagement.features.private_jobs.di.privateJobsModule
import com.ierusalem.employeemanagement.features.profile.di.profileModule
import com.ierusalem.employeemanagement.features.staff_home.di.staffHomeScreenModule
import com.ierusalem.employeemanagement.features.work_description.di.workDescriptionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EmployeeManagementApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Channel name",
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
                homeModule,
                privateJobsModule,
                profileModule,
                editProfileViewModule,
                composeModule,
                staffHomeScreenModule,
                workDescriptionModule
            )
        }
    }
}