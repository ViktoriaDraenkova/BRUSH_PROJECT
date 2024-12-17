package com.ru.practicum.usmeshka_groovy.domain.achievement.impl

import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementInteractor
import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementsRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Achievement
import kotlinx.coroutines.flow.Flow

class AchievementInteractorImpl(val achievementsRepository: AchievementsRepository) :
    AchievementInteractor {
    override suspend fun getAchievements(): Flow<List<Achievement>> {
        return achievementsRepository.getAchievements()
    }
}