package com.ru.practicum.usmeshka_groovy.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ru.practicum.usmeshka_groovy.domain.models.RepeatInterval

@Entity(tableName = "notification_table")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,
    val repeating: RepeatInterval,
    val message: String,
)

