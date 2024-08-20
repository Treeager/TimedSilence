package com.timedsilence

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        // Update the shared preference
        log("Finished")
        return Result.success()
    }
}


class MyViewModel: ViewModel() {
    private val _shouldActivate = MutableStateFlow(false)
    val shouldActivate: StateFlow<Boolean> = _shouldActivate.asStateFlow()


    init {
        log("Class initialized")
    }

//    val time: Long = 2

    // Schedules the work
    fun scheduleWork(context: Context, time: Long) {
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(time, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(context)
            .enqueue(workRequest)

        // Listen for the result and update the state
        WorkManager.getInstance(context)
        .getWorkInfoByIdLiveData(workRequest.id)
        .observeForever { workInfo ->
            if (workInfo != null && workInfo.state.isFinished) {
                log("Should update")
                _shouldActivate.update { true }
                log("after work = ${_shouldActivate.value}")
            }
        }
    }

    fun resetActivationState() {
        _shouldActivate.update { false }
    }
}