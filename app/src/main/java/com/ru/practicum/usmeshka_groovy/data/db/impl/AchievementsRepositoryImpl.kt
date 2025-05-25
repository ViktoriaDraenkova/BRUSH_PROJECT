package com.ru.practicum.usmeshka_groovy.data.db.impl

import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementsRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Achievement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AchievementsRepositoryImpl : AchievementsRepository {
    override suspend fun getAchievements(): Flow<List<Achievement>> = flow {
        emit(
            listOf(
                Achievement("Ты молодец!", "В понедельник ты почистил зубы 2 раза!", 2),
//                Achievement("Так держать!", "Ты чистил зубы каждый день на этой неделе!", 2),
//                Achievement("Отличное выполнение!", "Ты не забыл почистить зубы перед сном!", 3),
//                Achievement("Молодец!", "Ты сделал это без напоминаний!", 7),
//                Achievement("Ты настоящий герой гигиены!", "Ты научился правильно чистить зубы!", 8)
            )
        )
    }
}