package com.example.docdash.notifications.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.notifications.FireNotification
import com.example.docdash.notifications.data.Notification
import com.example.docdash.notifications.viewmodel.NotificationViewModel
import com.example.docdash.queueTimeCircle.components.themeColor
import java.util.*

val themeColors = themeColor

@Composable
fun SetupNotifications(
    context: Context,
    notificationViewModel: NotificationViewModel
) {

    val allNotifications by notificationViewModel.notificationList.observeAsState(listOf())

    AddNotificationBox(
        context = context,
        notificationViewModel = notificationViewModel,
        allNotifications = allNotifications
    )

}

@Composable
fun AddNotificationBox(
    context: Context,
    notificationViewModel: NotificationViewModel,
    allNotifications: List<Notification>
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedButton(
            onClick = {
                insertDataToDatabase(
                    context,
                    type = 1,
                    title = "Emergency",
                    description = "The hospital has received an emergency. Your waiting time as" +
                            "increased with 30 minutes",
                    timeAdded = 30,
                    notificationViewModel = notificationViewModel
                )
            }
        ) {
            Text(text = "Fire Emergency")
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Text(
            text = ("Notifications").uppercase(),
            color = themeColor.primaryColorDark
        )

        LazyColumn(

        ) {
            item(

            ) {

            }
            items(
                allNotifications
            ) { notification ->
                NotificationBar(
                    type = notification.type,
                    title = notification.title,
                    timeAdded = notification.timeAdded
                )

            }
        }
    }

}

@Composable
fun NotificationBar(
    type: Int,
    title: String,
    timeAdded: Int
) {
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentAlignment = Alignment.Center

        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(320.dp)
                    .height(50.dp)
                    .background(Color.White)
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Notification",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(25.dp)
                )
                Spacer(
                    modifier = Modifier
                        .padding(25.dp)
                )
                Text(
                    text = title.uppercase(Locale.getDefault()),
                    color = if (type == 1) Color.Red else Color.White,
                    fontSize = 12.sp

                )
                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    text = "(+$timeAdded minutes)",
                    color = if (type == 1) Color.Red.copy(alpha = 0.3f)
                    else Color.White.copy(alpha = 0.3f),
                    fontSize = 12.sp
                )
            }
        }
    }
}

fun insertDataToDatabase(
    context: Context,
    type: Int,
    title: String,
    description: String,
    timeAdded: Int,
    notificationViewModel: NotificationViewModel
) {
    val notificationSetup = FireNotification(context = context, title = title, msg = description)
    notificationSetup.emergencyNotification()

    val notification = Notification(0, type, title, description, timeAdded)
    notificationViewModel.addNotification(notification)
    Toast.makeText(context, "Added notification", Toast.LENGTH_LONG).show()
}
