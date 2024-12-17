package com.ru.practicum.usmeshka_groovy.data.db

import android.util.Log
import androidx.room.TypeConverter
import com.ru.practicum.usmeshka_groovy.domain.models.RepeatInterval

class Converters {
    @TypeConverter
    fun toRepeatInterval(value: Int): RepeatInterval {
        Log.d(value.toString(), enumValues<RepeatInterval>().toString())
        return enumValues<RepeatInterval>()[value]
    }
    @TypeConverter
    fun fromRepeatInterval(value: RepeatInterval) = value.ordinal
}