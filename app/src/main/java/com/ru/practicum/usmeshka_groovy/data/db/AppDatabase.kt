package com.ru.practicum.usmeshka_groovy.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ru.practicum.usmeshka_groovy.data.db.dao.AnalyticsDao
import com.ru.practicum.usmeshka_groovy.data.db.dao.NotificationDao
import com.ru.practicum.usmeshka_groovy.data.db.entity.DataForAnalyticsEntity
import com.ru.practicum.usmeshka_groovy.data.db.entity.NotificationEntity

@Database(
    version = 1,
    entities = [NotificationEntity::class, DataForAnalyticsEntity::class]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
    abstract fun analyticsDao(): AnalyticsDao
}
