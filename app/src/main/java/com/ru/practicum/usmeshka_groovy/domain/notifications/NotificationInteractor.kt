package com.ru.practicum.usmeshka_groovy.domain.notifications

import com.ru.practicum.usmeshka_groovy.domain.models.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationInteractor {
    suspend fun getNotifications(): Flow<List<Notification>>
    suspend fun insertNotification(notification: Notification)
    suspend fun deleteNotifications(notification: Notification)
}