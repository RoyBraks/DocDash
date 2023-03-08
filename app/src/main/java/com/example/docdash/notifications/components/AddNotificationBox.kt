package com.example.docdash.notifications.components

import android.content.Context
import android.widget.Toast
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.docdash.notifications.FireNotification
import com.example.docdash.notifications.data.Notification
import com.example.docdash.notifications.viewmodel.NotificationViewModel

private lateinit var notificationViewModel: NotificationViewModel

@Composable
fun AddNotificationBox(
    context: Context,
    owner: ViewModelStoreOwner
){
    notificationViewModel = ViewModelProvider(owner).get(NotificationViewModel::class.java)

    OutlinedButton(
        onClick = {
            insertDataToDatabase(
                context,
                type = 1,
                title = "Emergency",
                description = "The hospital has received an emergency. Your waiting time as" +
                        "increased with 30 minutes",
                timeAdded = 30,
            )
        }
    ) {
        Text(text = "Fire Emergency")
    }
}

fun insertDataToDatabase(
    context: Context,
    type: Int,
    title: String,
    description: String,
    timeAdded: Int
) {
    val notificationSetup = FireNotification(context = context, title = title, msg = description)
    notificationSetup.emergencyNotification()

    val notification = Notification(0, type, title, description, timeAdded)
    notificationViewModel.addNotification(notification)
    Toast.makeText(context, "Added notification", Toast.LENGTH_LONG).show()
}
