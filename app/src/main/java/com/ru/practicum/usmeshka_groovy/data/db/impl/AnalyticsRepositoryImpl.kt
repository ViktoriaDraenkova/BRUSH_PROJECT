package com.ru.practicum.usmeshka_groovy.data.db.impl

import com.ru.practicum.usmeshka_groovy.data.db.AppDatabase
import com.ru.practicum.usmeshka_groovy.data.db.entity.DataForAnalyticsEntity
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnalyticsRepositoryImpl(private val appDatabase: AppDatabase) : AnalyticsRepository {
    override suspend fun getAllAnal(): Flow<List<Long>> = flow {
        emit(
            appDatabase.analyticsDao().getAllAnalytics().map { anal ->
                mapEntityToLong(anal)
            })
    }

    override suspend fun deleteAnal(date: Long) {
        appDatabase.analyticsDao().deleteFromAnalytics(date)
    }

    override suspend fun insertAnal(date: Long) {
        appDatabase.analyticsDao().insertToAnalytics(DataForAnalyticsEntity(dataOfCleaning = date))
    }

    private fun mapEntityToLong(analyticsEntity: DataForAnalyticsEntity): Long {
        return analyticsEntity.dataOfCleaning
    }
}