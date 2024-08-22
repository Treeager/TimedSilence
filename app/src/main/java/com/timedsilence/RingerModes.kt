package com.timedsilence

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.provider.Settings
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver

fun setRingerMode(context: Context, mode: Int) {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    audioManager.ringerMode = mode
}


// RingerModeReceiver listens for the alarm broadcast and changes the ringer mode.
@SuppressLint("RestrictedApi")
class RingerModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        // Retrieve the ringer mode passed with the Intent.
        val mode = intent?.getIntExtra("RINGER_MODE", AudioManager.RINGER_MODE_NORMAL)

        // Get the AudioManager service to change the ringer mode.
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager


        // Set the ringer mode to the desired mode (Silent, Vibrate, or Normal).
        mode?.let {
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL // If this isn't here sometimes doesn't switch  from vibration to silent
            audioManager.ringerMode = it
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