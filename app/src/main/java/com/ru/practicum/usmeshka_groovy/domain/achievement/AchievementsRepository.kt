package com.ru.practicum.usmeshka_groovy.domain.achievement

import com.ru.practicum.usmeshka_groovy.domain.models.Achievement
import kotlinx.coroutines.flow.Flow

interface AchievementsRepository {
    suspend fun getAchievements(): Flow<List<Achievement>>
}