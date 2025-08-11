package com.kalori.tounsi.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WorkoutReminderWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "workout_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(NotificationChannel(channelId, "Sport", NotificationManager.IMPORTANCE_DEFAULT))
        }
        val notif = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("🏃‍♂️ وقت الرياضة!")
            .setContentText("مشي سريع 30د / HIIT خفيف / مقاومة")
            .build()
        nm.notify(1002, notif)
        return Result.success()
    }
}
