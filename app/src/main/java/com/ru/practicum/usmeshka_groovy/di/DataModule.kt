package com.ru.practicum.usmeshka_groovy.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ru.practicum.usmeshka_groovy.data.db.AppDatabase
import com.ru.practicum.usmeshka_groovy.data.prefs.PreferencesManagerImpl
import com.ru.practicum.usmeshka_groovy.domain.prefs.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        synchronized(this) {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db").build()
        }
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    single<PreferencesManager> { PreferencesManagerImpl(get()) }
}