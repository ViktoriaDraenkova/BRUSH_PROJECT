package com.ru.practicum.usmeshka_groovy.util

import com.ru.practicum.usmeshka_groovy.domain.models.Product
import com.ru.practicum.usmeshka_groovy.domain.models.Purchase

fun interface ProductClickListener {
    fun onProductClick(product: Product)
}

fun interface ButtonAddClickListener {
    fun onButtonAddClickListener(product: Product): Boolean
}

fun interface ButtonDelClickListener {
    fun onButtonDelClickListener(product: Product): Boolean
}

fun interface PurchaseClickListener {
    fun onPurchaseClick(purchase: Purchase)
}
