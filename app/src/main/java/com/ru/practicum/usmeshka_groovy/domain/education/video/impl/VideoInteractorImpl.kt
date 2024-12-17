package com.ru.practicum.usmeshka_groovy.domain.education.video.impl

import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoInteractor
import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Video
import kotlinx.coroutines.flow.Flow

class VideoInteractorImpl(private val videoRepository: VideoRepository): VideoInteractor {
    override suspend fun getVideos(): Flow<List<Video>> {
        return videoRepository.getVideos()
    }
}