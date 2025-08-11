package com.kalori.tounsi.util

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.kalori.tounsi.data.prefs.Prefs
import java.util.Calendar

object ReminderScheduler {
    fun scheduleAll(ctx: Context) {
        val prefs = Prefs(ctx)
        val goal = runBlocking { prefs.goal.first() } // lose | maintain | gain
        scheduleWater(ctx, goal)
        scheduleWorkout(ctx, goal)
    }

    private fun scheduleWater(ctx: Context, goal: String) {
        val intervalHours = when(goal) {
            "lose" -> 2L
            "maintain" -> 3L
            "gain" -> 4L
            else -> 3L
        }
        val req = PeriodicWorkRequestBuilder<WaterReminderWorker>(intervalHours, TimeUnit.HOURS)
            .addTag("water_reminder")
            .build()
        WorkManager.getInstance(ctx).cancelAllWorkByTag("water_reminder")
        WorkManager.getInstance(ctx).enqueueUniquePeriodicWork("water_reminder", ExistingPeriodicWorkPolicy.REPLACE, req)
    }

    private fun scheduleWorkout(ctx: Context, goal: String) {
        val wm = WorkManager.getInstance(ctx)
        wm.cancelAllWorkByTag("workout_reminder")

        fun scheduleAt(hour: Int, minute: Int) {
            val now = Calendar.getInstance()
            val first = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                if (before(now)) add(Calendar.DAY_OF_YEAR, 1)
            }
            val initialDelay = first.timeInMillis - now.timeInMillis
            val req = PeriodicWorkRequestBuilder<WorkoutReminderWorker>(24, TimeUnit.HOURS)
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .addTag("workout_reminder")
                .build()
            wm.enqueue(req)
        }

        when(goal) {
            "lose" -> { scheduleAt(8,30); scheduleAt(18,30) }
            "maintain" -> scheduleAt(18,0)
            "gain" -> scheduleAt(19,0)
            else -> scheduleAt(18,0)
        }
    }
}
