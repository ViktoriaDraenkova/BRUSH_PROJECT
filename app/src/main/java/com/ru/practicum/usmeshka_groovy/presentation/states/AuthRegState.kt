package com.ru.practicum.usmeshka_groovy.presentation.states

import com.ru.practicum.usmeshka_groovy.domain.models.User

sealed interface AuthRegState {
    data object Loading : AuthRegState

    data object Filling : AuthRegState

    data class Authenticated(val user: User) : AuthRegState

    data class Error(val message: String) : AuthRegState
}
