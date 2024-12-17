package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ru.practicum.usmeshka_groovy.domain.prefs.PrefsManagerInteractor

class RegistrationViewModel(private val prefsManagerInteractor: PrefsManagerInteractor) :
    ViewModel() {
    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name

    private val _surname = MutableLiveData<String?>()
    val surname: LiveData<String?> = _surname

    fun saveWhoUse(selectedId: Int, radioButtonText: String) {
        prefsManagerInteractor.saveWhoUse(selectedId, radioButtonText)
    }

    fun saveNameSurname(name: String, surname: String) {
        prefsManagerInteractor.saveNameSurname(name, surname)
        _name.value = name
        _surname.value = surname
    }

    fun saveNotificationsIsNeed(selectedId: Int, radioButtonText: String) {
        prefsManagerInteractor.saveNotificationsIsNeed(selectedId, radioButtonText)
    }

    fun saveEmail(email: String) {
        prefsManagerInteractor.saveEmail(email)
    }
    fun saveRegistration(isCompleted: Boolean) {
        prefsManagerInteractor.setRegistrationCompleted(isCompleted)
    }
}