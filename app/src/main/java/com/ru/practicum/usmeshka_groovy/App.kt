package com.ru.practicum.usmeshka_groovy

import android.app.Application
import com.practicum.usmeshka_groovy.BuildConfig
import com.ru.practicum.usmeshka_groovy.di.dataModule
import com.ru.practicum.usmeshka_groovy.di.interactorModule
import com.ru.practicum.usmeshka_groovy.di.repositoryModule
import com.ru.practicum.usmeshka_groovy.di.utilModule
import com.ru.practicum.usmeshka_groovy.di.viewModelModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)

        startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule, utilModule)
        }
    }
}