package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.authorisation.AuthInteractor
import kotlinx.coroutines.launch

class SettingsViewModel(val autInteractor: AuthInteractor): ViewModel() {

    private val uidLiveData: MutableLiveData<String> = MutableLiveData()

    fun getuidLivedata(): LiveData<String>{
        return uidLiveData
    }

    fun getUid(){
        viewModelScope.launch {
            autInteractor.getCurrentUser().collect { user ->
                if (user.isSuccess && user.getOrNull() != null) {
                    uidLiveData.value = user.getOrNull()?.id
                }
            }
        }
    }
}