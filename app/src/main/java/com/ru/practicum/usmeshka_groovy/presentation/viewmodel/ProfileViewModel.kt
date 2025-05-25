package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.models.Purchase
import com.ru.practicum.usmeshka_groovy.domain.authorisation.AuthInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.User
import com.ru.practicum.usmeshka_groovy.domain.purchases.PurchasesInteractor
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val authInteractor: AuthInteractor,
    private val purchasesInteractor: PurchasesInteractor
) : ViewModel() {
    private val _personalInfoLiveData = MutableLiveData<User?>()
    val personalLiveData: LiveData<User?> = _personalInfoLiveData
    private val _purchasesLiveData = MutableLiveData<List<Purchase>>()
    val purchasesLiveData: LiveData<List<Purchase>> = _purchasesLiveData

    fun setPersonalInfo() {
        viewModelScope.launch {
            authInteractor.getCurrentUser().collect { result ->
                _personalInfoLiveData.value = result.getOrNull()
            }
        }
    }

    fun getPurchases() {
        viewModelScope.launch {
            purchasesInteractor.getPurchases(_personalInfoLiveData.value!!).collect { result ->
                if (result.isSuccess) {
                    _purchasesLiveData.value = result.getOrNull()!!
                }
            }
        }
    }
}