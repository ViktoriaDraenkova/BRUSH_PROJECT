package com.ru.practicum.usmeshka_groovy.domain.education.articles.impl

import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesInteractor
import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticlesInteractorImpl(val articlesRepository: ArticlesRepository) : ArticlesInteractor {
    override suspend fun getArticles(): Flow<List<Article>> = flow {
        val listOfArticles = mutableListOf<Article>()
        val flow = articlesRepository.getArticles()
        flow.collect { articles ->
            for (article in articles) {
                listOfArticles.add(article)
            }
            emit(listOfArticles)
        }
    }
}