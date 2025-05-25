package com.ru.practicum.usmeshka_groovy.domain.analytics.impl

import android.util.Log
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsInteractor
import com.ru.practicum.usmeshka_groovy.domain.analytics.AnalyticsRepository
import kotlin.math.roundToInt

class AnalyticsInteractorImpl(val analyticsRepository: AnalyticsRepository) : AnalyticsInteractor {
    override suspend fun getDataForGraph(date: Long): List<Pair<Long, Int>> {
        val listp = mutableMapOf<Long, Int>()
        val flow = analyticsRepository.getAllAnal()
        flow.collect { list ->
            for (i in list) {
                if (i >= date - 7 * 86400) {
                    listp[i] = (listp[i] ?: 0) + 1
                }
            }
        }
        for (i in date downTo  date - 7 * 86400 + 1 step 86400) {
            if (!listp.contains(i)) {
                listp[i] = 0
            }
        }
        return listp.toList()
    }

    override suspend fun getCountOfCleans(date: Long): Int {
        var counter = 0
        val flow = analyticsRepository.getAllAnal()
        flow.collect { list ->
            for (i in list) {
                if (i == date) {
                    counter++
                }
            }
        }
        return counter
    }

    override suspend fun getCountOfDaysWithoutBreaks(date: Long): Int {
        val flow = analyticsRepository.getAllAnal()
        var counter = 0
        flow.collect { list ->
            val set = HashSet<Long>(list)
            Log.d("", list.toString())
            Log.d("", set.toString())
            var curDay = date - 86400
            while (true){
                if (set.contains(curDay)){
                    counter++
                    curDay -= 86400
                } else {
                    break
                }
            }
            if (set.contains(date)) {
                counter++
            }

        }
        return counter
    }

    override suspend fun getMiddleCountOfCleansPerDay(): Int {
        val listp = mutableMapOf<Long, Int>()
        val flow = analyticsRepository.getAllAnal()
        flow.collect { list ->
            for (i in list) {
                listp[i] = (listp[i] ?: 0) + 1
            }
        }
        val res = listp.values.average()
        return if (!res.isNaN()) res.roundToInt() else 0
    }

    override suspend fun addClean(date: Long) {
        analyticsRepository.insertAnal(date)
    }
}