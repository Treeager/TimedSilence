package com.timedsilence

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.provider.Settings

fun setRingerMode(context: Context, mode: Int) {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    audioManager.ringerMode = mode
}

@SuppressLint("ServiceCast")
fun setSilentMode(context: Context) {
//    setRingerMode(context, AudioManager.RINGER_MODE_SILENT)
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.isNotificationPolicyAccessGranted) {
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
            audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
        } else {
            // Request permission from the user to access DND
            context.startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
        }
    } else {
        audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
    }
}

fun setVibrateMode(context: Context) {
    setRingerMode(context, AudioManager.RINGER_MODE_VIBRATE)
}

fun setNormalMode(context: Context) {
    setRingerMode(context, AudioManager.RINGER_MODE_NORMAL)
}