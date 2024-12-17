package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.Article
import kotlinx.coroutines.launch

class ArticlesViewModel(val articlesInteractor: ArticlesInteractor) : ViewModel() {
    private val articlesLiveData = MutableLiveData<List<Article>>()
    fun getArticles() {
        viewModelScope.launch {
            articlesInteractor.getArticles().collect {
                articlesLiveData.value = it
            }
        }
    }

    fun getAarticlesLiveData(): LiveData<List<Article>> {
        return articlesLiveData
    }
}