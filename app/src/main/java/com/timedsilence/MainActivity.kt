package com.timedsilence

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.timedsilence.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        checkAndRequestExactAlarmPermission(this) // TODO: Dialog about permissions
        setContent {
            AppTheme {
                MainComposable(AlarmViewModel())
            }
        }
    }
}

fun log(msg: String = "Here", tag: String = "ok") {
    Log.i(tag, msg)
}


@Preview(showBackground = true )
@Composable
fun MainPreview() {
    AppTheme {
        MainComposable(AlarmViewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialer(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()
    val showingPicker = remember { mutableStateOf(true) }

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )



    Dialog(onDismissRequest = onDismiss ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 24.dp)
                    .width(intrinsicSize = IntrinsicSize.Max)
            ) {
                if (showingPicker.value) {
                    TimePicker( modifier = Modifier.fillMaxWidth(), state = timePickerState )
                } else {
                    TimeInput( modifier = Modifier.fillMaxWidth(), state = timePickerState )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    IconButton(onClick = {
                        showingPicker.value = !showingPicker.value},

                        ) {
                        val icon =
                            if (showingPicker.value) {
                                Icons.Outlined.Edit
                            } else {
                                Icons.Outlined.KeyboardArrowDown
                            }

                        Icon(
                           icon,
                            contentDescription = "Switch time input methods"
                        )
                    }
                    Row (horizontalArrangement = Arrangement.SpaceBetween) {
                        TextButton(
                            onClick = onDismiss,

                            ) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = { onConfirm(timePickerState) } ) {
                            Text(text = "Ok")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopAndBottomBars (onClicked: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold (
/*        topBar = {
            Box {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                        containerColor = MaterialTheme.colorScheme.primaryContainer,
//                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = { Text("Timed Silence") })
            }
        },*/

        floatingActionButton = {
            FloatingActionButton(
                onClick = onClicked,
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                modifier = Modifier
                    .size(70.dp)
                    .offset(y = 70.dp)

            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "Start",
                    modifier = Modifier.size(30.dp)
                )
            }

        },
        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {
            BottomAppBar (
                modifier = Modifier.offset(y = 15.dp),
                actions = {
                    IconButton(
                        onClick = {
                            showBottomSheet = true
                        }
                    ) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        }
    ) {
        if (showBottomSheet) {
            ModalBottomSheet( // TODO: Add some options
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                modifier = Modifier
                    .height(300.dp)
                ) {

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 80.dp)
                ) {
                    Text("Text")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetMode(viewModel: AlarmViewModel) {
    var selectedIndex by remember { mutableStateOf(0)}
    var mode by remember { mutableStateOf(2) }
    val options = listOf("Normal", "Vibrate", "Silent")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index

                    when(selectedIndex) {
                        0 -> mode = 2
                        1 -> mode = 1
                        2 -> mode = 0
                    }

                    viewModel.changeMode(mode)
                },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(label)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable (viewModel: AlarmViewModel) {
    val context = LocalContext.current
    val mode by viewModel.mode.collectAsState()
    log("Main called")

    var showDialer by remember { mutableStateOf(false) }
    var selectedTime: TimePickerState? by remember { mutableStateOf(null) }
    var hours by remember { mutableStateOf("00") }
    var minutes by remember { mutableStateOf("00") }

    if (selectedTime != null) {
        hours = selectedTime?.hour.toString()

        minutes = if (selectedTime?.minute != 0) {
            selectedTime?.minute.toString()
        } else {
            "00"
        }

//        if (minutes.length == 1) {
//            minutes = "0$minutes"
//        }

    }


    TopAndBottomBars {
//        viewModel.scheduleWork(context, time)
        scheduleRingerModeChange(context, mode, hours.toInt(), minutes.toInt())
        log("$mode, ${hours.toInt()}, ${minutes.toInt()}")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 65.dp)
    ) {
        Text(text = "Timed Silence",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
//            .width(IntrinsicSize.Min)

    )  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = " ${if (hours.length==1) "0$hours" else hours }:${if (minutes.length==1) "0$minutes" else minutes}",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth()
            )

            // Segmented buttons and mode logic
            SetMode(viewModel)


            Button(onClick = {
                showDialer = true
            }) {
                Text("Select time")
            }
        }

//        AnimatedVisibility(
//            visible = showDialer,
//            enter = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow)),
//            exit = fadeOut()
//        ) {
        if (showDialer) {
            Dialer(
                onDismiss = { showDialer = false },
                onConfirm = { time ->
                    selectedTime = time
                    showDialer = false
                }
            )
        }

    }
}
