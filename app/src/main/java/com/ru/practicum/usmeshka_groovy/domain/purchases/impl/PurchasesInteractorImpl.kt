package com.ru.practicum.usmeshka_groovy.domain.purchases.impl

import com.ru.practicum.usmeshka_groovy.domain.models.Purchase
import com.ru.practicum.usmeshka_groovy.domain.purchases.PurchasesInteractor
import com.ru.practicum.usmeshka_groovy.domain.purchases.PurchasesRepository
import com.ru.practicum.usmeshka_groovy.domain.models.User
import kotlinx.coroutines.flow.Flow

class PurchasesInteractorImpl(private val purchasesRepository: PurchasesRepository): PurchasesInteractor {
    override suspend fun getPurchases(user: User): Flow<Result<List<Purchase>>> {
        return purchasesRepository.getPurchases(user)
    }

    override suspend fun addPurchase(
        user: User,
        purchase: Purchase
    ): Flow<Result<Unit>> {
        return purchasesRepository.addPurchase(user, purchase)
    }
}