package com.ru.practicum.usmeshka_groovy.data.articles

import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticlesRepositoryImpl : ArticlesRepository {
    override fun getArticles(): Flow<List<Article>> = flow {
        emit(
            listOf(
                Article(
                    "Гигиена полости рта как профилактика стоматологических заболеваний",
                    "https://eduherald.ru/ru/article/view?id=15409",
                    ""
                ),
                Article(
                    "Гигиена полости рта",
                    "https://cgon.rospotrebnadzor.ru/naseleniyu/zdorovyy-obraz-zhizni/gigiena-polosti-rta/",
                    ""
                ),
                Article(
                    "Правила ухода за полостью рта",
                    "https://polza.ru/blog/poleznaya-informatsiya/pravila-ukhoda-za-polostyu-rta/?utm_referrer=https%3A%2F%2Fyandex.by%2F",
                    ""
                ),
                Article(
                    "Гигиена полости рта",
                    "https://uteka.ru/articles/zdorove/gigiena-polosti-rta/",
                    ""
                ),
                Article(
                    "Правила гигиены полости рта",
                    "https://yandex.ru/health/turbo/articles?id=8151",
                    ""
                ),
                Article(
                    "Чистим зубки правильно!",
                    "https://vocmp.oblzdrav.ru/chistim-zubki-pravilno-pravila-gigieni.html",
                    ""
                ),

                Article(
                    "Уход за полостью рта у ребенка",
                    "https://www.listerine.ru/vash-gid-po-uhodu-za-polostju-rta/uhod-za-polostyu-rta-u-rebenka",
                    ""
                ),
                Article(
                    "Как правильно соблюдать гигиену полости рта детям и что нужно знать об этом родителям?",
                    "https://beautymed-48.ru/articles/kak-pravilno-soblyudat-gigienu-polosti-rta-detyam-i-chto-nuzhno-znat-ob-etom-roditelyam",
                    ""
                ),
            )
        )
    }
}