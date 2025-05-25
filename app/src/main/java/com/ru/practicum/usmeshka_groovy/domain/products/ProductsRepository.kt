package com.ru.practicum.usmeshka_groovy.domain.products

import com.ru.practicum.usmeshka_groovy.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getAllProducts(): Flow<Result<List<Product>>>
}