package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.info.news.NewsInteractor
import com.ru.practicum.usmeshka_groovy.domain.info.sales.SalesInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.News
import com.ru.practicum.usmeshka_groovy.domain.models.Sale
import kotlinx.coroutines.launch

class NewsSalesViewModel(
    private val newsInteractor: NewsInteractor,
    private val salesInteractor: SalesInteractor
) : ViewModel() {
    private val newsLiveData = MutableLiveData<List<News>>()
    private val salesLiveData = MutableLiveData<List<Sale>>()

    fun getNews() {
        viewModelScope.launch {
            newsInteractor.getNews().collect {
                newsLiveData.value = it
            }
        }
    }

    fun getNewsLiveData(): LiveData<List<News>> {
        return newsLiveData
    }

    fun getSales() {
        viewModelScope.launch {
            salesInteractor.getSales().collect {
                salesLiveData.value = it
            }
        }
    }

    fun getSalesLiveData(): LiveData<List<Sale>> {
        return salesLiveData
    }
}