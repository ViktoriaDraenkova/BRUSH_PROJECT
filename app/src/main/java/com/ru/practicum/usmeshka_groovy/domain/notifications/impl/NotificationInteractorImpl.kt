package com.ru.practicum.usmeshka_groovy.domain.notifications.impl

import com.ru.practicum.usmeshka_groovy.domain.models.Notification
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationInteractor
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationRepository
import kotlinx.coroutines.flow.Flow

class NotificationInteractorImpl(private val repository: NotificationRepository) :
    NotificationInteractor {
    override suspend fun getNotifications(): Flow<List<Notification>> {
        return repository.getNotifications()
    }

    override suspend fun insertNotification(notification: Notification) {
        repository.insertNotification(notification)
    }

    override suspend fun deleteNotifications(notification: Notification) {
        repository.deleteNotifications(notification)
    }
}