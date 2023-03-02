package com.example.docdash.queueTimeCircle.utils

import java.util.concurrent.TimeUnit



object Utility {
    private val timeDifference = timeUntilAppointment()
    var TOTAL_TIME = timeDifference
    private const val TIME_FORMAT = "%02d:%02d:%02d"

    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toHours(this) % 24,
        TimeUnit.MILLISECONDS.toMinutes(this) % 60,
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}