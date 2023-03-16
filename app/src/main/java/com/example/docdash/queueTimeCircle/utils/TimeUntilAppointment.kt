package com.example.docdash.queueTimeCircle.utils

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun timeUntilAppointment(): Long {
    val date = LocalDateTime.now()

    val dateAppointment = LocalDateTime.parse("2023-03-16T01:27:00")

    var timeDifference = date.until(dateAppointment, ChronoUnit.MILLIS)

    if (date.until(dateAppointment, ChronoUnit.MILLIS) <= 0f) {
        timeDifference = 0
    }
    return timeDifference
}