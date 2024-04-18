package com.ierusalem.employeemanagement.features.notification

import android.app.NotificationManager
import android.content.Context
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
        Log.d("ahi3646", "onMessageReceived: ${message.data} ")
        Log.d("ahi3646", "onMessageReceived: ${message.data.entries} ")
        Log.d("ahi3646", "onMessageReceived: ${message.data.values} ")
        showNotification(this.resources.getString(R.string.app_name),
            getString(R.string.new_message))
    }

    private fun showNotification(title:String, description: String) {
        val notification = NotificationCompat.Builder(applicationContext, "channel_id")
            .setSmallIcon(R.drawable.plus)
            .setContentTitle(title)
            .setContentText(description)
            .build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

}