package com.ru.practicum.usmeshka_groovy.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ru.practicum.usmeshka_groovy.data.db.entity.NotificationEntity

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notificationEntity: NotificationEntity)

    @Delete(entity = NotificationEntity::class)
    suspend fun deleteNotification(notificationEntity: NotificationEntity)

    @Query("SELECT * FROM notification_table")
    suspend fun getNotifications(): List<NotificationEntity>
}