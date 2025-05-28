package com.ru.practicum.usmeshka_groovy.domain.analytics

import kotlinx.coroutines.flow.Flow

interface AnalyticsRepository {
    suspend fun getAllAnal(childId: String): Flow<List<Long>>
    suspend fun deleteAnal(childId: String, date: Long)
    suspend fun insertAnal(childId: String, date: Long)
}