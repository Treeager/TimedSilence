package com.timedsilence

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.media.AudioManager
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver
import com.timedsilence.ui.theme.primaryContainerDark
import com.timedsilence.ui.theme.primaryContainerLight
import com.timedsilence.ui.theme.secondaryContainerDark
import com.timedsilence.ui.theme.secondaryContainerLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun setRingerMode(context: Context, mode: Int) {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    audioManager.ringerMode = mode
}
val context = MyApplication.instance

fun isDarkModeEnabled(): Boolean {
    return (context.resources.configuration.uiMode
            and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}

// This code is disgusting and I'm not proud of it
val colorPrimaryContainer = if (isDarkModeEnabled()) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicDarkColorScheme(context).primaryContainer
    } else {
        primaryContainerDark
    }
}
else {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicLightColorScheme(context).primaryContainer
    } else {
        primaryContainerLight
    }
}

val colorSecondaryContainer = if (isDarkModeEnabled()) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicDarkColorScheme(context).secondaryContainer
    } else {
        secondaryContainerDark
    }
}
else {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicLightColorScheme(context).secondaryContainer
    } else secondaryContainerLight
}

val viewModel = AlarmViewModel(
    colorPrimaryContainer,
    colorSecondaryContainer,
    ScheduledData(context)
)

// RingerModeReceiver listens for the alarm broadcast and changes the ringer mode.
@SuppressLint("RestrictedApi")
class RingerModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        // Retrieve the ringer mode passed with the Intent.
        val mode = intent?.getIntExtra("RINGER_MODE", AudioManager.RINGER_MODE_NORMAL)

        // Get the AudioManager service to change the ringer mode.
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        makeToast(context, text = "Your phone is now in ${when(mode) {
            0 -> "silent"
            1 -> "vibration"
            2 -> "normal"
            else -> "this should NEVER show"
        }} mode")

        // Set the ringer mode to the desired mode (Silent, Vibrate, or Normal).
        mode?.let {
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL // If this isn't here sometimes doesn't switch  from vibration to silent
            audioManager.ringerMode = it
        }

        viewModel.changeFabShape(CircleShape)
        viewModel.changeFabColor(colorPrimaryContainer)
        viewModel.changeFabIcon(Icons.Filled.PlayArrow)
        val dataStore = ScheduledData(context)

        CoroutineScope(Dispatchers.IO).launch {
            dataStore.changeIsScheduled(false)
        }
    }
}

fun checkAndRequestDndPermission(context: Context ) {
    // Request permission from the user to access DND if it is not granted
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Check if the DND access is granted
    if (!notificationManager.isNotificationPolicyAccessGranted) {
        // If not granted, prompt the user to grant DND access
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}