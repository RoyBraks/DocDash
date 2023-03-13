package com.example.docdash.notifications.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.notifications.data.Notification
import com.example.docdash.notifications.data.NotificationDatabase
import com.example.docdash.notifications.data.NotificationRepository


class NotificationViewModel(
    application: Application
) : ViewModel()
{
    val notificationList: LiveData<List<Notification>>
    private val repository: NotificationRepository
    init {
        val notificationDatabase = NotificationDatabase.getDatabase(application)
        val notificationDao = notificationDatabase.notificationDao()
        repository = NotificationRepository(notificationDao)

        notificationList = repository.readAllData
    }

    fun addNotification(notification: Notification) {
        repository.addNotification(notification)
    }

    fun deleteAll(){
        repository.deleteAll()
    }
}

