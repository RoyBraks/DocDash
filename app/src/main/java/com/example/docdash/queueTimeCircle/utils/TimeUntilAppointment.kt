package com.example.docdash.queueTimeCircle.utils

import android.app.Application
import com.example.docdash.notifications.viewmodel.NotificationViewModel
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


fun timeUntilAppointment(): Long {
    val date = LocalDateTime.now()

    val notificationViewModel = NotificationViewModel(application = Application())

    val dateAppointment = LocalDateTime.parse("2023-03-13T15:56:00")

    var timeDifferenceDates = date.until(dateAppointment, ChronoUnit.MILLIS)

    if (date.until(dateAppointment, ChronoUnit.MILLIS) <= 0f) {
        timeDifferenceDates = 0
    }
    return timeDifferenceDates
}