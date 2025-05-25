package com.ru.practicum.usmeshka_groovy.domain.info.news.impl

import com.ru.practicum.usmeshka_groovy.domain.info.news.NewsInteractor
import com.ru.practicum.usmeshka_groovy.domain.info.news.NewsRepository
import com.ru.practicum.usmeshka_groovy.domain.models.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsInteractorImpl(private val newsRepository: NewsRepository) : NewsInteractor {
    override suspend fun getNews(): Flow<List<News>> = flow {
        val listOfNews = mutableListOf<News>()
        val flow = newsRepository.getNews()
        flow.collect { news ->
            for (new in news) {
                listOfNews.add(new)
            }
            emit(listOfNews)
        }
    }
}