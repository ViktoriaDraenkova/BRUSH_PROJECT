package com.ru.practicum.usmeshka_groovy.domain.info.sales

import com.ru.practicum.usmeshka_groovy.domain.models.Sale
import kotlinx.coroutines.flow.Flow

interface SalesRepository {
    fun getSales(): Flow<List<Sale>>

}