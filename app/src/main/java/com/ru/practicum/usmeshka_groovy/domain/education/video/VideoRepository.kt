package com.ru.practicum.usmeshka_groovy.domain.education.video

import com.ru.practicum.usmeshka_groovy.domain.models.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getVideos(): Flow<List<Video>>
}