package com.ru.practicum.usmeshka_groovy.presentation.states

import com.ru.practicum.usmeshka_groovy.domain.models.Product


sealed interface ProductsScreenState {
    data object Loading : ProductsScreenState

    data class Content(val data: List<Product>) : ProductsScreenState

    data object Error : ProductsScreenState
}