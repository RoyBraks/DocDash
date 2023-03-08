package com.example.docdash.notifications.data

import androidx.lifecycle.LiveData

class NotificationRepository(
    private val notificationDao: NotificationDao
) {
    val readAllData: LiveData<List<Notification>> = notificationDao.readAllData()

    suspend fun addNotification(notification: Notification){
        notificationDao.addNotification(notification = notification)
    }
}