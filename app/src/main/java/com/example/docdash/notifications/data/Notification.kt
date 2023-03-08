package com.example.docdash.notifications.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_table")
data class Notification (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val type: Int,
    val title: String,
    val description: String,
    val timeAdded: Int,
)