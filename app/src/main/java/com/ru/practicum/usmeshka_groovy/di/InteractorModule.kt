package com.ru.practicum.usmeshka_groovy.di

import com.practicum.testappshop.domain.cart.CartInteractor
import com.practicum.testappshop.domain.cart.impl.CartInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementInteractor
import com.ru.practicum.usmeshka_groovy.domain.achievement.impl.AchievementInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsInteractor
import com.ru.practicum.usmeshka_groovy.domain.analytics.impl.AnalyticsInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.authorisation.AuthInteractor
import com.ru.practicum.usmeshka_groovy.domain.authorisation.impl.AuthInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesInteractor
import com.ru.practicum.usmeshka_groovy.domain.education.articles.impl.ArticlesInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoInteractor
import com.ru.practicum.usmeshka_groovy.domain.education.video.impl.VideoInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.info.news.NewsInteractor
import com.ru.practicum.usmeshka_groovy.domain.info.news.impl.NewsInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.info.sales.SalesInteractor
import com.ru.practicum.usmeshka_groovy.domain.info.sales.impl.SalesInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationInteractor
import com.ru.practicum.usmeshka_groovy.domain.notifications.impl.NotificationInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.prefs.PrefsManagerInteractor
import com.ru.practicum.usmeshka_groovy.domain.prefs.impl.PrefsManagerInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.products.ProductsInteractor
import com.ru.practicum.usmeshka_groovy.domain.products.impl.ProductsInteractorImpl
import com.ru.practicum.usmeshka_groovy.domain.purchases.PurchasesInteractor
import com.ru.practicum.usmeshka_groovy.domain.purchases.impl.PurchasesInteractorImpl
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
    single<SalesInteractor> {
        SalesInteractorImpl(get())
    }
    single<NewsInteractor> {
        NewsInteractorImpl(get())
    }

    single<ProductsInteractor> {
        ProductsInteractorImpl(get())
    }
    single<CartInteractor> {
        CartInteractorImpl(get())
    }
    single<AuthInteractor> {
        AuthInteractorImpl(get())
    }

    single<PurchasesInteractor> {
        PurchasesInteractorImpl(get())
    }

}