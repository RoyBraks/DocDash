package com.example.docdash.notifications.viewmodel

import android.app.Application
import com.example.docdash.notifications.data.NotificationDatabase
import com.example.docdash.notifications.data.NotificationRepository

class NotificationApplication : Application()  {
    val database by lazy { NotificationDatabase.getDatabase(this) }
    val repository by lazy { NotificationRepository(database.notificationDao()) }
}