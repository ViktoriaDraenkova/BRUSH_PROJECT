package com.ru.practicum.usmeshka_groovy.domain.models

enum class RepeatInterval(val strValue: String) {
    EVERY_DAY("Повторять каждый день"),
    EVERY_WEEK("Повторять раз в неделю"),
    EVERY_2WEEKS("Повторять каждые 2 недели"),
    EVERY_3WEEKS("Повторять каждые 3 недели"),
    EVERY_MONTH("Повторять каждый месяц"),
    EVERY_3MONTH("Повторять каждые 3 месяца"),
    EVERY_HALF_YEAR("Повторять каждые полгода"),
    EVERY_YEAR("Повторять каждый год"),
}
