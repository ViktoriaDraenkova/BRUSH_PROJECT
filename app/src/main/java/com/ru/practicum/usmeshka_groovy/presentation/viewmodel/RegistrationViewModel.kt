package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.authorisation.AuthInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.User
import com.ru.practicum.usmeshka_groovy.domain.prefs.PrefsManagerInteractor
import com.ru.practicum.usmeshka_groovy.presentation.states.AuthRegState
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val prefsManagerInteractor: PrefsManagerInteractor,
    private val authInteractor: AuthInteractor
) : ViewModel() {
    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name
    private val _authStateLiveData = MutableLiveData<AuthRegState>(AuthRegState.Filling)
    val authStateLiveData: LiveData<AuthRegState> get() = _authStateLiveData
//
//    fun isAuthorised() {
//        viewModelScope.launch {
//            authInteractor.getCurrentUser().collect { user ->
//                _userLiveData.postValue(user.getOrNull())
//            }
//        }
//    }

    fun register(login: String, password: String, user: User) {
        _authStateLiveData.postValue(AuthRegState.Loading)
        viewModelScope.launch {
            authInteractor.register(login, password, user).collect { result ->
                if (result.isSuccess) {
                    _authStateLiveData.postValue(AuthRegState.Authenticated(result.getOrNull()!!))
                } else {
                    _authStateLiveData.postValue(AuthRegState.Error(result.exceptionOrNull()?.message.toString()))
                    _authStateLiveData.postValue(AuthRegState.Filling)
                }
            }
        }
    }

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