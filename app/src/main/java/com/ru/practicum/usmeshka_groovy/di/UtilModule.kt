package com.ru.practicum.usmeshka_groovy.di

import com.ru.practicum.usmeshka_groovy.domain.mapper.NotificationsConverter
import org.koin.dsl.module

val utilModule = module {
    factory {
        NotificationsConverter()
    }
}