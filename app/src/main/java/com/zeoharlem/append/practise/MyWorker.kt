package com.zeoharlem.append.practise

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {

    companion object {
        val CHANNEL_ID  = "smoothers"
    }

    private fun showNotification(){
        createNotification()
        val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("This is the testing change")
            .setContentTitle("What are you waiting for")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        notificationBuilder.setContentIntent(openPendingStartActivity())

        val notificationManagerCompact  = NotificationManagerCompat.from(applicationContext)
        notificationManagerCompact.notify(123456, notificationBuilder.build())
    }

    private fun createNotification(){
        val name: CharSequence  = "MyNotification"
        val description         = "My Notification Channel Description"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                this.description    = description
            }
            val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun openPendingStartActivity(): PendingIntent? {
        val mainIntent = Intent(applicationContext, MainActivity::class.java)
        mainIntent.putExtra("fullname", "Theophilus Alamu")
        mainIntent.putExtra("email", "theophilus.alamu8@gmail.com")
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        return PendingIntent.getActivity(
            applicationContext,
            1,
            mainIntent,
            PendingIntent.FLAG_ONE_SHOT
        )
    }

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }
}