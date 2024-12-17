package com.ru.practicum.usmeshka_groovy.domain.mapper

import com.ru.practicum.usmeshka_groovy.data.db.entity.NotificationEntity
import com.ru.practicum.usmeshka_groovy.domain.models.Notification

class NotificationsConverter {
    private fun mapEntityToNotifications(entity: NotificationEntity): Notification {
        return Notification(
            entity.id,
            entity.date,
            entity.repeating,
            entity.message,
        )
    }

    fun mapNotificationToEntity(entity: Notification): NotificationEntity {
        return NotificationEntity(
            date=entity.date, repeating = entity.repeating, message = entity.message
        )
    }

    fun mapList(list: List<NotificationEntity>): List<Notification> {
        return list.map {
            mapEntityToNotifications(it)
        }
    }
}