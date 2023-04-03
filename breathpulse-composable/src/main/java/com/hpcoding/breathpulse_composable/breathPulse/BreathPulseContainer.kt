package com.hpcoding.breathpulse_composable.breathPulse

import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hpcoding.breathpulse_composable.R
import java.util.*

@Composable
fun BreathPulseContainer(
    surfaceColor: Color = Color(0xff1f1f1f),
    pulseColor: Color = Color.Cyan,
    isTimerEnabled: Boolean = true,
    timerTextStyle: TextStyle = MaterialTheme.typography.h4.copy(color = Color.White),
    messageTextStyle: TextStyle = MaterialTheme.typography.h6.copy(color = Color.White),
    breathConfig: BreathConfig = BreathConfig()

) {

    val context = LocalContext.current
    val state: BreathViewState = rememberBreathViewState().also {
        it.setConfig(
            relaxTime = 5,
            cycles = breathConfig.cycle,
            inhale = breathConfig.inhaleTime,
            exhale = breathConfig.exhaleTime,
            inhaleHold = breathConfig.inhaleHoldTime,
            exhaleHold = breathConfig.exhaleHoldTime
        )
    }

    val textToSpeech = remember {
        TextToSpeech(context) { status ->
            Log.e("hhp", "Text to speech status $status")
            if (status != TextToSpeech.SUCCESS)
                Toast.makeText(
                    context,
                    "Voice over feature not found in your device.",
                    Toast.LENGTH_SHORT
                ).show()
        }.also {
            it.language = Locale.ENGLISH
            it.setSpeechRate(.6f)
            it.setPitch(1.1f)
        }
    }

    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.audio).also {
            it.isLooping = true
        }
    }

    DisposableEffect(true) {
        onDispose() {
            mediaPlayer?.reset()
            mediaPlayer?.stop()
            textToSpeech.shutdown()
        }
    }

    val message = state.message.collectAsState(initial = "")

    val duration = state.duration.collectAsState(initial = "")


    LaunchedEffect(key1 = message.value, block = {
        if (message.value.isNotEmpty())
            textToSpeech.speak(message.value, TextToSpeech.QUEUE_FLUSH, null, null)
    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(surfaceColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = duration.value, style = messageTextStyle)

        Spacer(modifier = Modifier.height(24.dp))

        BreathView(state = state, color = pulseColor, timerTextStyle = timerTextStyle)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = message.value, style = messageTextStyle)

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = state.currentMode.value == BreathViewState.Mode.IDLE,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Button(onClick = {
                state.startExercise()
                try {
                    mediaPlayer.start()
                    mediaPlayer.isLooping = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }) {
                Text(text = "Start")
            }
        }
    }
}

