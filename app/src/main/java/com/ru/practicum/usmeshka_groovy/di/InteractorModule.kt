package com.ru.practicum.usmeshka_groovy.di

import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementInteractor
import com.ru.practicum.usmeshka_groovy.domain.achievement.impl.AchievementInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsInteractor
import com.ru.practicum.usmeshka_groovy.domain.analytics.impl.AnalyticsInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesInteractor
import com.ru.practicum.usmeshka_groovy.domain.education.articles.impl.ArticlesInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoInteractor
import com.ru.practicum.usmeshka_groovy.domain.education.video.impl.VideoInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationInteractor
import com.ru.practicum.usmeshka_groovy.domain.notifications.impl.NotificationInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.prefs.PrefsManagerInteractor
import com.ru.practicum.usmeshka_groovy.domain.prefs.impl.PrefsManagerInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<NotificationInteractor> {
        NotificationInteractorImpl(get())
    }

    single<AnalyticsInteractor> {
        AnalyticsInteractorImpl(get())
    }

    single<AchievementInteractor> {
        AchievementInteractorImpl(get())
    }
    single<ArticlesInteractor> {
        ArticlesInteractorImpl(get())
    }
    single<PrefsManagerInteractor> {
        PrefsManagerInteractorImpl(get())
    }
    single<VideoInteractor> {
        VideoInteractorImpl(get())
    }

}