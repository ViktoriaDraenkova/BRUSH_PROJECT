package com.ru.practicum.usmeshka_groovy.di

import com.ru.practicum.usmeshka_groovy.data.articles.ArticlesRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.db.impl.AchievementsRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.db.impl.AnalyticsRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.db.impl.NotificationRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.prefs.PreferencesManagerImpl
import com.ru.practicum.usmeshka_groovy.data.video.VideoRepositoryImpl
import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementsRepository
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsRepository
import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesRepository
import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoRepository
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationRepository
import com.ru.practicum.usmeshka_groovy.domain.prefs.PreferencesManager
import org.koin.dsl.module

val repositoryModule = module {
    single<NotificationRepository> {
        NotificationRepositoryImpl(get(), get())
    }
    single<AnalyticsRepository> {
        AnalyticsRepositoryImpl(get())
    }
    single<AchievementsRepository> {
        AchievementsRepositoryImpl()
    }
    single<ArticlesRepository> {
        ArticlesRepositoryImpl()
    }
    single<PreferencesManager> {
        PreferencesManagerImpl(get())
    }
    single<VideoRepository> {
        VideoRepositoryImpl()
    }
}