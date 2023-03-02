package com.example.docdash.queueTimeCircle.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun timeUntilAppointment(): Long {
    val date = LocalDateTime.now()

    val dateAppointment = LocalDateTime.parse("2023-03-02T17:10:00")

    var timeDifference = date.until(dateAppointment, ChronoUnit.MILLIS)

    if (date.until(dateAppointment, ChronoUnit.MILLIS) <= 0f) {
        timeDifference = 0
    }

    return timeDifference
}