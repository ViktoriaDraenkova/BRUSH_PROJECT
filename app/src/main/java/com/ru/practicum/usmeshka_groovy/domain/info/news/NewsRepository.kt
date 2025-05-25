package com.ru.practicum.usmeshka_groovy.domain.info.news

import com.ru.practicum.usmeshka_groovy.domain.models.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<List<News>>
}