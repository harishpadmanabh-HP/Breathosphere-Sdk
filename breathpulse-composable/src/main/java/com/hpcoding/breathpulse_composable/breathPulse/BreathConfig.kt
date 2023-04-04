package com.hpcoding.breathpulse_composable.breathPulse

import androidx.compose.ui.graphics.Color

/**
 * BreathConfig class is used to store the configurations for breathing pulse view which should be passed to @see [BreathPulseContainer]
 * Default values set as below
 * @param inhaleTime - 4 -> time for single inhaling time in seconds
 * @param exhaleTime - 4 -> time for single exhale time in seconds
 * @param inhaleHoldTime - 0 -> time for single inhale hold in seconds
 * @param exhaleHoldTime - 0 -> time for single exhale hold in seconds
 * @param cycle - 2 -> Number of breath cycles
 * @param pulseColor - Cyan -> Color for the breathing pulse animating circles
 * @param surfaceColor -Dark Gary -> Color for the background container
 * @param showHoldTimer - false -> if true , timer is shown at the hold time or else a pause icon is shown.
 */
data class BreathConfig(
    val inhaleTime: Int = 4,
    val exhaleTime: Int = 4,
    val inhaleHoldTime: Int = 0,
    val exhaleHoldTime: Int = 0,
    val cycle: Int = 2,
    val pulseColor: Color = Color.Cyan,
    val surfaceColor: Color = Color(0xff1f1f1f),
    val showHoldTimer:Boolean = false
)