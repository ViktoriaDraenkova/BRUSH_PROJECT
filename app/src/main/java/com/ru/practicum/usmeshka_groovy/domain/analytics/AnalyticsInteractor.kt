package com.ru.practicum.usmeshka_groovy.domain.analytics

interface AnalyticsInteractor {
    suspend fun getDataForGraph(date : Long): List<Pair<Long, Int>>
    suspend fun getCountOfCleans(date: Long): Int
    suspend fun getCountOfDaysWithoutBreaks(date: Long): Int
    suspend fun getMiddleCountOfCleansPerDay(): Int
    suspend fun addClean(date: Long)
}