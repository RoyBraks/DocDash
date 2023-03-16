package com.example.docdash.notifications

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.NotificationManagerCompat

class permission {
    companion object {


        @Composable
        fun NotificationPermissionDialog(
            onConfirm: () -> Unit,
            onDismiss: () -> Unit
        ) {
            AlertDialog(
                title = { Text("Enable Notifications") },
                text = { Text("To receive important updates, please enable notifications for this app.") },
                confirmButton = {
                    TextButton(onClick = {
                        onConfirm()
                    }) {
                        Text("Enable Notifications")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        onDismiss()
                    }) {
                        Text("Cancel")
                    }
                },
                onDismissRequest = {
                    onDismiss()
                },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            )
        }

        @Composable
        fun RequestNotificationPermission() {
            val context = LocalContext.current
            val showDialog = remember { mutableStateOf(!areNotificationsEnabled(context)) }

            if (showDialog.value) {
                NotificationPermissionDialog(
                    onConfirm = {
                        // Open the app's notification settings when the user confirms
                        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        }
                        context.startActivity(intent)
                        showDialog.value = false
                    },
                    onDismiss = {
                        // Dismiss the dialog when the user cancels
                        showDialog.value = false
                    }
                )
            }
        }

        fun areNotificationsEnabled(context: Context): Boolean {
            val notificationManager = NotificationManagerCompat.from(context)
            return notificationManager.areNotificationsEnabled()
        }
    }
}