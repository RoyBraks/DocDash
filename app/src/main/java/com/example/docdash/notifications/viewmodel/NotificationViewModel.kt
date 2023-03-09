package com.example.docdash.notifications.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.docdash.notifications.data.Notification
import com.example.docdash.notifications.data.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationViewModel(private val repository: NotificationRepository): AndroidViewModel(application = Application()) {

    val readAllData: LiveData<List<Notification>> = repository.readAllData
    fun addNotification(notification: Notification) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotification(notification = notification)
        }
    }
}

