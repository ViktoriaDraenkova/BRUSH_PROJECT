package com.ru.practicum.usmeshka_groovy.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "analytics_table")

data class DataForAnalyticsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dataOfCleaning:Long,
)