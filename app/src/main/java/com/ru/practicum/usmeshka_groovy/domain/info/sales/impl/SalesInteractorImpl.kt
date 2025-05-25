package com.ru.practicum.usmeshka_groovy.domain.info.sales.impl

import com.ru.practicum.usmeshka_groovy.domain.info.sales.SalesInteractor
import com.ru.practicum.usmeshka_groovy.domain.info.sales.SalesRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Sale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SalesInteractorImpl(private val salesRepository: SalesRepository) : SalesInteractor {

    override suspend fun getSales(): Flow<List<Sale>> = flow {
        val listOfSales = mutableListOf<Sale>()
        val flow = salesRepository.getSales()
        flow.collect { sales ->
            for (sale in sales) {
                listOfSales.add(sale)
            }
            emit(listOfSales)
        }
    }
}