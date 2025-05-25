package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.testappshop.domain.cart.CartInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.Product
import com.ru.practicum.usmeshka_groovy.domain.products.ProductsInteractor
import com.ru.practicum.usmeshka_groovy.presentation.states.ProductsScreenState
import kotlinx.coroutines.launch

class ProductsListFragmentViewModel(
    private val productsInteractor: ProductsInteractor,
    private val cartInteractor: CartInteractor,
) : ViewModel() {
    private val screenStateLiveData = MutableLiveData<ProductsScreenState>()

    fun getScreenLiveData(): LiveData<ProductsScreenState> = screenStateLiveData

    fun getProducts() {
        viewModelScope.launch {
            screenStateLiveData.value = ProductsScreenState.Loading
            productsInteractor.getProducts().collect { result ->
                if (result.isFailure) {
                    screenStateLiveData.postValue(ProductsScreenState.Error)
                } else {
                    screenStateLiveData.postValue(ProductsScreenState.Content(result.getOrNull()!!))
                }
            }
        }
    }

    fun increaseInCart(product: Product): Boolean {
        return cartInteractor.addProductToCart(product)
    }

    fun decreaseInCart(product: Product): Boolean {
        return cartInteractor.deleteProductFromCart(product)
    }

    fun getCountInCart(productList: List<Product>): List<Long> {
        return productList.map { product -> cartInteractor.getCountInCart(product) }
    }
}