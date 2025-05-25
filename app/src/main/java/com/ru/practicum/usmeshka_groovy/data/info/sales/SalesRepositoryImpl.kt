package com.ru.practicum.usmeshka_groovy.data.info.sales

import com.ru.practicum.usmeshka_groovy.domain.info.sales.SalesRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Sale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SalesRepositoryImpl : SalesRepository {
    override fun getSales(): Flow<List<Sale>> = flow {
        emit(
            listOf(
                Sale(
                    1,
                    "https://fb.ru/misc/i/gallery/26435/1409750.jpg",
                ),

                Sale(
                    2,
                    "https://medsprint.ru/assets/images/blog/blog_zakon_o_reklame_2.jpg",
                ),
                Sale(
                    3,
                    "https://i.pinimg.com/originals/49/ca/35/49ca35f9dbbf8921d24cbb22954d02a5.jpg",
                ),
                Sale(
                    4,
                    "https://vitacorclinic.ru/wp-content/uploads/2022/11/otbel-2-1536x864.png",
                ),
            )
        )
    }
}