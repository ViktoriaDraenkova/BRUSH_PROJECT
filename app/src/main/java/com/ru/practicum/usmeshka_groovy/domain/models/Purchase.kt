package com.ru.practicum.usmeshka_groovy.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Purchase(
    val datetime: String,
    val items: List<PurchaseItem>
) : Parcelable {
    constructor() : this("", listOf())
}

@Parcelize
data class PurchaseItem(
    val product: Product,
    val count: Int,
) : Parcelable {
    constructor() : this(Product(), 0)
}
