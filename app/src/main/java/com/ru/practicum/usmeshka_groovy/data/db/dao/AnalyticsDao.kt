package com.ru.practicum.usmeshka_groovy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ru.practicum.usmeshka_groovy.data.db.entity.DataForAnalyticsEntity

@Dao
interface AnalyticsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToAnalytics(dataForAnalyticsEntity: DataForAnalyticsEntity)

    @Query("DELETE FROM analytics_table WHERE dataOfCleaning < :before")
    suspend fun deleteFromAnalytics(before: Long)

    @Query("SELECT * FROM analytics_table")
    suspend fun getAllAnalytics(): List<DataForAnalyticsEntity>
}