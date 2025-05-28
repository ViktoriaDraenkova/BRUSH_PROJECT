package com.ru.practicum.usmeshka_groovy.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String,
    var surname: String,
    var patronymic: String,
    var isChild: Boolean,
    var childId: String?,
) : Parcelable {
    constructor() : this("", "", "", "", false, null)
}