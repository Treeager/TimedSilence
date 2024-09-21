package com.timedsilence

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch




// Schedules an alarm to change the ringer mode at a specific time.
@SuppressLint("ScheduleExactAlarm")
fun scheduleRingerModeChange(context: Context, mode: Int, hour: Int, minute: Int, cancel: Boolean, modeInText: String = when(mode) {
    2 -> "normal"
    1 -> "vibration"
    0 -> "silent"
    else -> {"this should NEVER show, report to the dev"}
}) {
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

    if (!cancel) {
        makeToast(context, text = "Schedule $modeInText change")
        // Schedule the alarm to go off at the exact time specified in the calendar.
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    } else {
        alarmManager.cancel(pendingIntent)
        makeToast(context, text = "Mode change on $hour:$minute canceled")
    }
}

fun checkAndRequestExactAlarmPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (! alarmManager.canScheduleExactAlarms()) {
            // Prompt the user to grant the permission
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}