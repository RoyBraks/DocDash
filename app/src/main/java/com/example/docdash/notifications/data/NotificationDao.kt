package com.example.docdash.notifications.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotification(notification: Notification)

    @Query("DELETE FROM notification_table")
    fun deleteAll()

    @Query("SELECT * FROM notification_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Notification>>

}