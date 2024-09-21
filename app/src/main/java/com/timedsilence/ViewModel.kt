package com.timedsilence

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

class ViewModel(
    colorPrimaryContainer: Color,
    colorSecondaryContainer: Color,
    private val scheduledData: ScheduledData
): ViewModel() {
    init {
        log("Class initialized")
    }
    val wasScheduled: LiveData<Boolean> = scheduledData.getIsScheduled().asLiveData()

    private val _fabShape = MutableStateFlow(CircleShape)
    val fabShape: StateFlow<RoundedCornerShape> = _fabShape.asStateFlow()

    private val _fabColor = MutableStateFlow(colorPrimaryContainer)
    val fabColor: StateFlow<Color> = _fabColor.asStateFlow()

    private val _fabIcon = MutableStateFlow(Icons.Filled.PlayArrow)
    val fabIcon: StateFlow<ImageVector> = _fabIcon.asStateFlow()

    fun changeFabShape(shape: RoundedCornerShape) {
        _fabShape.update { shape }
    }

    fun changeFabColor(color: Color) {
        _fabColor.update { color }
    }

    fun changeFabIcon(icon: ImageVector) {
        _fabIcon.update { icon }
    }

    init {
        // Observe wasScheduled LiveData and update _fabShape accordingly
        viewModelScope.launch {
            wasScheduled.asFlow().collect { isScheduled ->
                _fabShape.value = if (isScheduled) RoundedCornerShape(16.dp)
                else CircleShape

                _fabColor.value = if(isScheduled) colorSecondaryContainer
                else colorPrimaryContainer

                _fabIcon.value = if (isScheduled) Icons.Filled.Clear
                else Icons.Filled.PlayArrow
            }
        }
    }

    private val _mode = MutableStateFlow(2)
    val mode: StateFlow<Int> = _mode.asStateFlow()

    fun changeMode(mode: Int) {
        _mode.update { mode }
    }

    private val _hour = MutableStateFlow(0)
    val hour: StateFlow<Int> = _mode.asStateFlow()
}