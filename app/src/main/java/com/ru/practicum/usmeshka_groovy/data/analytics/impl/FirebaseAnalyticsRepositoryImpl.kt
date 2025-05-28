package com.ru.practicum.usmeshka_groovy.data.analytics.impl

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAnalyticsRepositoryImpl(val fbdatabase: FirebaseDatabase) : AnalyticsRepository {
    override suspend fun getAllAnal(childId: String): Flow<List<Long>> = flow {
        try {
            val snapshot = fbdatabase.getReference("statistics").child(childId).get().await()
            if (snapshot.exists()) {
                val result = mutableListOf<Long>()
                for (child in snapshot.children) {
                    result.add(child.getValue(Long::class.java)!!)
                }
                Log.d("GOT RES", result.toString())
                emit(result)
            } else {
                Log.d("NO RESULT", "NO RES")
                emit(emptyList())
            }
        } catch (e: Exception) {
            Log.e("ERROR products", e.message.toString())
            emit(emptyList())
        }
    }

    override suspend fun deleteAnal(childId: String, date: Long) {
        try {
            val snapshot = fbdatabase.getReference("statistics").child(childId).get().await()
            if (snapshot.exists()) {
                val result = mutableListOf<Long>()
                for (child in snapshot.children) {
                    val value = child.getValue(Long::class.java)!!
                    if (value >= date) {
                        result.add(value)
                    }
                }
                fbdatabase.getReference("statistics").child(childId).removeValue()
                fbdatabase.getReference("statistics").child(childId).setValue(result)

//                for (value in result) {
//                    insertAnal(childId, value)
//                }
            } else {
                Log.d("NO RESULT", "NO RES")
            }
        } catch (e: Exception) {
            Log.e("ERROR products", e.message.toString())
        }
    }

    override suspend fun insertAnal(childId: String, date: Long) {

        fbdatabase.getReference("statistics").child(childId).push().setValue(date).await()

    }
}