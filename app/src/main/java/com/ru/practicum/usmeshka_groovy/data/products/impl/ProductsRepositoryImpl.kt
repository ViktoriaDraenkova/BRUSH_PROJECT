package com.ru.practicum.usmeshka_groovy.data.products.impl

import com.ru.practicum.usmeshka_groovy.data.db.entity.FullPackEntity
import com.ru.practicum.usmeshka_groovy.domain.models.Product
import com.ru.practicum.usmeshka_groovy.data.db.AppDatabase
import com.ru.practicum.usmeshka_groovy.domain.products.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsRepositoryImpl(private val database: AppDatabase) :
    ProductsRepository {
    override fun getAllProducts(): Flow<Result<List<Product>>> = flow {
        val result = try {
            val data = database.packDao().getAllPacks()
            Result.success(data.map { fullPackEntity ->
                entityToPackConverter(fullPackEntity)
            })
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(
            result
        )
    }

    private fun entityToPackConverter(fullPackEntity: FullPackEntity): Product {
        return Product(
            id = fullPackEntity.packEntity.id,
            title = fullPackEntity.packEntity.name,
            stock = fullPackEntity.packEntity.quant,
            price = fullPackEntity.packPriceEntity.price,
            sale = fullPackEntity.packPriceEntity.bonus,
            pricePer = fullPackEntity.packEntity.type,
            unitName = fullPackEntity.unitEntity?.name ?: "",
            barcode = fullPackEntity.barcodeEntity.body,
            imageUrl = ""
        )
    }
}