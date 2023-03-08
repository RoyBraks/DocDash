package com.example.docdash.notifications.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.notifications.data.Notification
import com.example.docdash.notifications.data.NotificationDatabase
import com.example.docdash.notifications.data.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationViewModel(application: Application): ViewModel() {

    private val readAllData: LiveData<List<Notification>>
    private val repository: NotificationRepository

    init {
        val notificationDao = NotificationDatabase.getDatabase(application).notificationDao()
        repository = NotificationRepository(notificationDao)
        readAllData = repository.readAllData
    }

    fun addNotification(notification: Notification) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotification(notification = notification)
        }
    }
}