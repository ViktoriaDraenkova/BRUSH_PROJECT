package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementInteractor
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.Achievement
import kotlinx.coroutines.launch

class AnalyticsViewModel(
    val analyticsInteractor: AnalyticsInteractor,
    val achievementInteractor: AchievementInteractor
) : ViewModel() {

    private val graphAnalLiveData = MutableLiveData<List<Pair<Long, Int>>>()
    fun getAnalForGraph(date: Long) {
        viewModelScope.launch {
            graphAnalLiveData.value = analyticsInteractor.getDataForGraph(date)
        }
    }

    fun getAnalyticsLiveData(): LiveData<List<Pair<Long, Int>>> {
        return graphAnalLiveData
    }

    private val achievementsLiveData = MutableLiveData<List<Achievement>>()

    fun getAchievementsLiveData(): LiveData<List<Achievement>> {
        return achievementsLiveData
    }

    fun getAchievements() {
        viewModelScope.launch {
            achievementInteractor.getAchievements().collect() {
                achievementsLiveData.value = it
            }
        }
    }

}