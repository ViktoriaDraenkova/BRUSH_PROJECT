package com.practicum.testappshop.data.purchases

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.ru.practicum.usmeshka_groovy.domain.models.Purchase
import com.ru.practicum.usmeshka_groovy.domain.models.User
import com.ru.practicum.usmeshka_groovy.domain.purchases.PurchasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PurchasesRepositoryImpl(private val database: FirebaseDatabase) : PurchasesRepository {

    override suspend fun getPurchases(user: User): Flow<Result<List<Purchase>>> = flow {
        try {
            val snapshot = database.getReference("purchases").child(user.id).get().await()
            if (snapshot.exists()) {
                val result = mutableListOf<Purchase>()
                for (child in snapshot.children) {
                    result.add(child.getValue(Purchase::class.java)!!)
                }
                Log.d("GOT RES", result.toString())
                emit(Result.success(result))
            } else {
                Log.d("NO RESULT", "NO RES")
                emit(Result.success(listOf()))
            }
        } catch (e: Exception) {
            Log.d("ERROR purchases", e.message.toString())
            emit(Result.failure(e))
        }

    }

    override suspend fun addPurchase(
        user: User,
        purchase: Purchase
    ): Flow<Result<Unit>> = flow {
        try {
            database.getReference("purchases").child(user.id).push().setValue(purchase).await()
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}