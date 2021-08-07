package com.kodluyoruz.mvvmandroid.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kodluyoruz.mvvmandroid.R
import com.kodluyoruz.mvvmandroid.ui.MainActivity

class MVVMFirebaseMessaginService : FirebaseMessagingService() {
    private val TAG = "PushNotification"
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Check if message contains a notification payload.
        if (message.data.isNotEmpty()) {
            handleNow(message)

        }
        message.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification("${it.body}")

        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.v(TAG, "PushNotification")
    }

    private fun handleNow(message: RemoteMessage) {
        message.notification?.body?.let {
            sendNotification(it)
        }
    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = "freemium"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle("Kodluyoruz BootCamp")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

}