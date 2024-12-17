package com.ru.practicum.usmeshka_groovy.util

import android.content.Context
import java.util.Calendar

fun getCurrentDate(): Long {
    val cal = Calendar.getInstance()
    cal.set(Calendar.MILLISECOND, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.HOUR, 0)
    return cal.time.time
}

fun Int.dpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).toInt()
}