package com.ru.practicum.usmeshka_groovy.domain.authorisation

import com.ru.practicum.usmeshka_groovy.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthInteractor {
    suspend fun register(login: String, password: String, user: User): Flow<Result<User>>
    suspend fun signIn(login: String, password: String): Flow<Result<User>>
    suspend fun getCurrentUser(): Flow<Result<User?>>
}