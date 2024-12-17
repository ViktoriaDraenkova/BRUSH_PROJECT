package com.ru.practicum.usmeshka_groovy.domain.analytics

import kotlinx.coroutines.flow.Flow

interface AnalyticsRepository {
    suspend fun getAllAnal(): Flow<List<Long>>
    suspend fun deleteAnal(date: Long)
    suspend fun insertAnal(date: Long)
}