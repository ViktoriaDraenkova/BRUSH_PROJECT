package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.testappshop.domain.cart.CartInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.Product

class ProductDetailsViewModel(private val cartInteractor: CartInteractor) : ViewModel() {

    private val countInCartLiveData = MutableLiveData<Long>()
    fun getCountInCartLiveData(): LiveData<Long> = countInCartLiveData

    fun increaseInCart(product: Product) {
        if (cartInteractor.addProductToCart(product)) {
            countInCartLiveData.value = countInCartLiveData.value!!+1
        }
    }

    fun decreaseInCart(product: Product) {
        if (cartInteractor.deleteProductFromCart(product)) {
            countInCartLiveData.value = countInCartLiveData.value!!-1
        }
    }

    fun init(product: Product) {
        countInCartLiveData.value = cartInteractor.getCountInCart(product)
    }
}