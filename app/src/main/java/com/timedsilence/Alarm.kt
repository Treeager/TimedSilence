package com.timedsilence

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ComponentActivity
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AlarmViewModel: ViewModel() {
    init {
        log("Class initialized")
    }

    private val _mode = MutableStateFlow(0)
    val mode: StateFlow<Int> = _mode.asStateFlow()

    fun changeMode(mode: Int) {
        _mode.update { mode }
        log("reached")
    }
}

// Schedules an alarm to change the ringer mode at a specific time.
@SuppressLint("ScheduleExactAlarm")
fun scheduleRingerModeChange(context: Context, mode: Int, hour: Int, minute: Int) {
    // Set the time for the alarm using a Calendar instance.
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)  // Set the hour
        set(Calendar.MINUTE, minute)     // Set the minute
        set(Calendar.SECOND, 0)          // Set seconds to zero
    }

    // Get the AlarmManager service.
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // Create an Intent that will be triggered by the alarm.
    val intent = Intent(context, RingerModeReceiver::class.java).apply {
        // Pass the desired ringer mode as an extra in the Intent.
        putExtra("RINGER_MODE", mode)
    }

    // Create a PendingIntent that will be broadcast by the AlarmManager.
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Schedule the alarm to go off at the exact time specified in the calendar.
    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
}

fun checkAndRequestExactAlarmPermission(@SuppressLint("RestrictedApi") activity: ComponentActivity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (!alarmManager.canScheduleExactAlarms()) {
            // Prompt the user to grant the permission
            val intent = Intent().apply {
                action = Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                data = Uri.parse("package:${activity.packageName}")
            }
            activity.startActivity(intent)
        }
    }
}