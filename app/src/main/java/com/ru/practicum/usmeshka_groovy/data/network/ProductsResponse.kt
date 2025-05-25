package com.practicum.testappshop.data.network

import com.ru.practicum.usmeshka_groovy.domain.models.Product

data class ProductsResponse(
    val products: List<Product>
)