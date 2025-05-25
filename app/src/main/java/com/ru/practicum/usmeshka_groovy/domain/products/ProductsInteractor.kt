package com.ru.practicum.usmeshka_groovy.domain.products

import com.ru.practicum.usmeshka_groovy.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsInteractor {

    fun getProducts(): Flow<Result<List<Product>>>

}