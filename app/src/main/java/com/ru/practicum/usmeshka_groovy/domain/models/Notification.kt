package com.ru.practicum.usmeshka_groovy.domain.models

data class Notification(
    val id: Long = 0,
    val date: Long,
    val repeating: RepeatInterval,
    val message: String = "",
)
