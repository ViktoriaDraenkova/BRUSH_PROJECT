package com.ru.practicum.usmeshka_groovy.domain.models

data class Video(
    val name: String,
    val preview: String,
    val videoUrl: String,
    val channelName: String,
) {
    constructor(
        name: String,
        id: String,
        channelName: String,
    ) : this(
        name,
        "https://rutube.ru/api/video/$id/thumbnail/?redirect=1",
        "https://rutube.ru/video/$id/",
        channelName
    )
}