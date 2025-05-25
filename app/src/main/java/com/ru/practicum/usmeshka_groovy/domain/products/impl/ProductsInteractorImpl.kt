package com.ru.practicum.usmeshka_groovy.domain.products.impl

import com.ru.practicum.usmeshka_groovy.domain.models.Product
import com.ru.practicum.usmeshka_groovy.domain.products.ProductsInteractor
import com.ru.practicum.usmeshka_groovy.domain.products.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsInteractorImpl(private val productsRepository: ProductsRepository) :
    ProductsInteractor {
    override fun getProducts(): Flow<Result<List<Product>>> {
        return productsRepository.getAllProducts()
    }
}