package com.ru.practicum.usmeshka_groovy.data.info.news

import com.ru.practicum.usmeshka_groovy.domain.info.news.NewsRepository
import com.ru.practicum.usmeshka_groovy.domain.models.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl : NewsRepository {


    override fun getNews(): Flow<List<News>> = flow {
        emit(
            listOf(
                News(
                    2,
                    "Новая стоматологическая клиника!",
                    "13.12.2024",
                    "https://medsprint.ru/assets/images/blog/blog_zakon_o_reklame_2.jpg",
                ),
                News(
                    1,
                    "Парадонтическая программа по ",
                    "21.12.2024",
                    "https://fb.ru/misc/i/gallery/26435/1409750.jpg"
                ),
                News(
                    3,
                    "Выпал зуб? Тогда мы идём к вам!",
                    "23.12.2024",
                    "https://i.pinimg.com/originals/49/ca/35/49ca35f9dbbf8921d24cbb22954d02a5.jpg",
                ),

                )
        )
    }
}