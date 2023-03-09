package com.example.docdash.notifications.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationRepository(
    private val notificationDao: NotificationDao
) {
    val readAllData: LiveData<List<Notification>> = notificationDao.readAllData()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addNotification(notification: Notification){
        coroutineScope.launch {
            notificationDao.addNotification(notification)
        }
    }
}