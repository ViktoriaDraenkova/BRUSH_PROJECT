package com.ru.practicum.usmeshka_groovy.data.db.impl

import com.ru.practicum.usmeshka_groovy.data.db.AppDatabase
import com.ru.practicum.usmeshka_groovy.data.db.entity.DataForAnalyticsEntity
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnalyticsRepositoryImpl(private val appDatabase: AppDatabase) : AnalyticsRepository {

    private fun mapEntityToLong(analyticsEntity: DataForAnalyticsEntity): Long {
        return analyticsEntity.dataOfCleaning
    }

    override suspend fun getAllAnal(childId: String): Flow<List<Long>> = flow {
        emit(
            appDatabase.analyticsDao().getAllAnalytics().map { anal ->
                mapEntityToLong(anal)
            })
    }

    override suspend fun deleteAnal(childId: String, date: Long) {
        appDatabase.analyticsDao().deleteFromAnalytics(date)
    }

    override suspend fun insertAnal(childId: String, date: Long) {
        appDatabase.analyticsDao().insertToAnalytics(DataForAnalyticsEntity(dataOfCleaning = date))    }
}