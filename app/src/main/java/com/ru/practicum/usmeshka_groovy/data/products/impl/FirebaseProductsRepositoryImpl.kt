package com.ru.practicum.usmeshka_groovy.data.products.impl

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.ru.practicum.usmeshka_groovy.domain.models.Product
import com.ru.practicum.usmeshka_groovy.domain.products.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseProductsRepositoryImpl(val fbdatabase: FirebaseDatabase) : ProductsRepository {
    override fun getAllProducts(): Flow<Result<List<Product>>> = flow {
        try {
            val snapshot = fbdatabase.getReference("products").get().await()
            if (snapshot.exists()) {
                val result = mutableListOf<Product>()
                for (child in snapshot.children) {
                    result.add(child.getValue(Product::class.java)!!)
                }
                Log.d("GOT RES", result.toString())
                emit(Result.success(result))
            } else {
                Log.d("NO RESULT", "NO RES")
                emit(Result.success(listOf()))
            }
        } catch (e: Exception) {
            Log.e("ERROR products", e.message.toString())
            emit(Result.failure(e))
        }
    }
}