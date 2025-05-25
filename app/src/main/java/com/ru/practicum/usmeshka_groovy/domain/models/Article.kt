package com.ru.practicum.usmeshka_groovy.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val name: String,
    val bodyHtml: String,
    val img: String,
) : Parcelable {
    constructor(): this("", "", "")
}