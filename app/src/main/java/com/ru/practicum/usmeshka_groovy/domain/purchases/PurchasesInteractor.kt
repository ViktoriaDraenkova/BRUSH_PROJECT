package com.ru.practicum.usmeshka_groovy.domain.purchases

import com.ru.practicum.usmeshka_groovy.domain.models.Purchase
import com.ru.practicum.usmeshka_groovy.domain.models.User
import kotlinx.coroutines.flow.Flow

interface PurchasesInteractor {
    suspend fun getPurchases(user: User): Flow<Result<List<Purchase>>>
    suspend fun addPurchase(user: User, purchase: Purchase):Flow<Result<Unit>>
}