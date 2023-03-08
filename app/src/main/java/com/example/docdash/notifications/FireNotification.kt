package com.example.docdash.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.docdash.MainActivity
import com.example.docdash.R

class FireNotification (
    var context: Context,
    var title: String,
    var msg: String,
    )
{
    private val channelID: String = "FCM100"
    private val channelName: String = "FCMMessage"
    private val notificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationBuilder: NotificationCompat.Builder

    fun emergencyNotification(
    ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE)

            notificationBuilder = NotificationCompat.Builder(context, channelID)
            notificationBuilder.setSmallIcon(R.drawable.baseline_notification_important_red_700_24dp)
            notificationBuilder.addAction(
                R.drawable.baseline_arrow_circle_right_red_700_24dp,
                "Open App",
                pendingIntent
            )
            notificationBuilder.setContentTitle(title)
            notificationBuilder.setContentText(msg)
            notificationBuilder.setAutoCancel(true)
            notificationManager.notify(100, notificationBuilder.build())
    }

    fun offsetNotification(
    ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE)

        notificationBuilder = NotificationCompat.Builder(context, channelID)
        notificationBuilder.setSmallIcon(R.drawable.baseline_circle_notifications_black_24dp)
        notificationBuilder.addAction(
            R.drawable.baseline_arrow_circle_right_black_24dp,
            "Open App",
            pendingIntent
        )
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(msg)
        notificationBuilder.setAutoCancel(true)
        notificationManager.notify(100, notificationBuilder.build())
    }
}