package com.superdemo.code.core.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.superdemo.code.R
import com.superdemo.code.features.mainmodule.MainParent
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    var type: String? = null

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.e("remote msg ${remoteMessage.data}")

        showNotification(
            remoteMessage.data["body"],
            remoteMessage.data["title"],
            remoteMessage.data["image"]
        )

        val data = remoteMessage.data
        Timber.d("notificationLOG   --   ${remoteMessage.data}")

        if (data.containsKey("type")) {
            type = data["type"]
        }

    }

    private fun showNotification(message: String?, title: String?, image: String?) {

        val channelId = "Notification Channel Flexline"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifyIntent = Intent(this, MainParent::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            this, 0, notifyIntent, PendingIntent.FLAG_MUTABLE
        )
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentIntent(notifyPendingIntent)
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setCategory(Notification.CATEGORY_PROMO)
            .setPriority(Notification.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()

            val channelQNS = NotificationChannel(
                channelId,
                "Flexline Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channelQNS)
            notificationManager.notify(0, notificationBuilder.build())
        }
    }

}