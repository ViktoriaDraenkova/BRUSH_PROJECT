package com.ru.practicum.usmeshka_groovy.data.db.impl

import com.ru.practicum.usmeshka_groovy.data.db.AppDatabase
import com.ru.practicum.usmeshka_groovy.domain.mapper.NotificationsConverter
import com.ru.practicum.usmeshka_groovy.domain.models.Notification
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotificationRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val converter: NotificationsConverter,

    ) : NotificationRepository {
    override suspend fun getNotifications(): Flow<List<Notification>> = flow {
        val list = appDatabase.notificationDao().getNotifications()
        emit(converter.mapList(list))
    }

    override suspend fun insertNotification(notification: Notification) {
        appDatabase.notificationDao()
            .insertNotification(converter.mapNotificationToEntity(notification))
    }

    override suspend fun deleteNotifications(notification: Notification) {
        appDatabase.notificationDao()
            .deleteNotification(converter.mapNotificationToEntity(notification))
    }
}