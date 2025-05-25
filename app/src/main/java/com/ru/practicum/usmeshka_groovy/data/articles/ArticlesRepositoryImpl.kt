package com.ru.practicum.usmeshka_groovy.data.articles

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.ru.practicum.usmeshka_groovy.domain.education.articles.ArticlesRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ArticlesRepositoryImpl(private val fbdatabase: FirebaseDatabase) : ArticlesRepository {
    override fun getArticles(): Flow<List<Article>> = flow {
        try {
            val snapshot = fbdatabase.getReference("articles").get().await()
            if (snapshot.exists()) {
                val result = mutableListOf<Article>()
                for (child in snapshot.children) {
                    result.add(child.getValue(Article::class.java)!!)
                }
                Log.d("GOT RES", result.toString())
                emit(result)
            } else {
                Log.d("NO RESULT", "NO RES")
                emit(listOf())
            }
        } catch (e: Exception) {
            Log.e("ERROR products", e.message.toString())
            emit(listOf())
        }
    }
}