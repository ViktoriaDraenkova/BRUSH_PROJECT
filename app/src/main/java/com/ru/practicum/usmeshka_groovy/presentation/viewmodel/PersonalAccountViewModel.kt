package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsInteractor
import com.ru.practicum.usmeshka_groovy.domain.analytics.impl.AnalyticsInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.models.Notification
import com.ru.practicum.usmeshka_groovy.domain.models.StatisticForProfile
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationInteractor
import com.ru.practicum.usmeshka_groovy.domain.prefs.PrefsManagerInteractor
import com.ru.practicum.usmeshka_groovy.util.getCurrentDate
import kotlinx.coroutines.launch

class PersonalAccountViewModel(
    private val notificationInteractor: NotificationInteractor,
    private val prefsManagerInteractor: PrefsManagerInteractor,
    private val analyticsInteractor: AnalyticsInteractor,
) :
    ViewModel() {
    private var notificationsLiveData = MutableLiveData<List<Notification>>()
    private var achivementsLiveData = MutableLiveData<StatisticForProfile>()
    fun getNotificationList() {
        viewModelScope.launch {
            notificationInteractor.getNotifications().collect { notifications ->
                notificationsLiveData.value = notifications
            }
        }
    }

    fun getCountAchievements() {
        viewModelScope.launch {
            val newStatistics = StatisticForProfile(
                analyticsInteractor.getCountOfDaysWithoutBreaks(
                    getCurrentDate()
                ),
                2,
                analyticsInteractor.getMiddleCountOfCleansPerDay()
            )
            achivementsLiveData.value = newStatistics
        }
    }

    fun getAchievementsLiveData():LiveData<StatisticForProfile>{
        return achivementsLiveData
    }

    fun getNameSurname(): String {
        return prefsManagerInteractor.getName() + " " + prefsManagerInteractor.getSurname()
    }

    fun insertNotification(notification: Notification) {
        viewModelScope.launch {
            notificationInteractor.insertNotification(notification)
        }
    }

    fun getNotificationsLiveData(): LiveData<List<Notification>> {
        return notificationsLiveData
    }


}