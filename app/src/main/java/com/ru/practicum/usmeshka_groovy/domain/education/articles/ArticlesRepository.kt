package com.ru.practicum.usmeshka_groovy.domain.education.articles

import com.ru.practicum.usmeshka_groovy.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getArticles(): Flow<List<Article>>
}