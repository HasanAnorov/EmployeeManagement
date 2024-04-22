package com.ierusalem.employeemanagement.features.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ierusalem.employeemanagement.R
import com.ierusalem.employeemanagement.ui.MainActivity
import com.ierusalem.employeemanagement.utils.Constants

class PushNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //update server
        Log.d("ahi3646", "onNewToken: $token ")
        val workManager = WorkManager.getInstance(this)
        val request = OneTimeWorkRequestBuilder<FirebaseTokenUpdateWorker>()
            .setInputData(
                workDataOf(
                    Constants.TOKEN_KEY_FOR_WORK_MANAGER to token
                )
            )
            .setConstraints(
                Constraints(requiredNetworkType = NetworkType.CONNECTED)
            )
            .build()
        workManager.enqueue(request)
        Log.d("ahi3646", "onMessageReceived: $token ")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(
            title = this.resources.getString(R.string.app_name),
            description = getString(R.string.new_message)
        )
    }

    private fun showNotification(title: String, description: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.NOTIFICATION, "notification")
        //fixme
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(
            this,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(applicationContext, "channel_id")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .build()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

}