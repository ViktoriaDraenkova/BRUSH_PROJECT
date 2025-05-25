package com.ru.practicum.usmeshka_groovy.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.ru.practicum.usmeshka_groovy.data.products.impl.ProductsRepositoryImpl
import com.practicum.testappshop.data.purchases.PurchasesRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.articles.ArticlesRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.authorisation.AuthRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.cart.CartRepository
import com.ru.practicum.usmeshka_groovy.data.cart.impl.CartRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.db.impl.AchievementsRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.db.impl.AnalyticsRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.db.impl.NotificationRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.info.news.NewsRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.info.sales.SalesRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.prefs.PreferencesManagerImpl
import com.ru.practicum.usmeshka_groovy.data.products.impl.FirebaseProductsRepositoryImpl
import com.ru.practicum.usmeshka_groovy.data.video.VideoRepositoryImpl
import com.ru.practicum.usmeshka_groovy.domain.achievement.AchievementsRepository
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsRepository
import com.ru.practicum.usmeshka_groovy.domain.authorisation.AuthRepository
import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesRepository
import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoRepository
import com.ru.practicum.usmeshka_groovy.domain.info.news.NewsRepository
import com.ru.practicum.usmeshka_groovy.domain.info.sales.SalesRepository
import com.ru.practicum.usmeshka_groovy.domain.notifications.NotificationRepository
import com.ru.practicum.usmeshka_groovy.domain.prefs.PreferencesManager
import com.ru.practicum.usmeshka_groovy.domain.products.ProductsRepository
import com.ru.practicum.usmeshka_groovy.domain.purchases.PurchasesRepository
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
    single<SalesRepository> {
        SalesRepositoryImpl()
    }
    single<NewsRepository> {
        NewsRepositoryImpl()
    }

    single<ProductsRepository> {
        FirebaseProductsRepositoryImpl(get())
    }
    single<CartRepository> {
        CartRepositoryImpl(get())
    }
    single<PurchasesRepository> {
        PurchasesRepositoryImpl(get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
}