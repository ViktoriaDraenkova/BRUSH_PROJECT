package com.ru.practicum.usmeshka_groovy.data.video

import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoRepository
import com.ru.practicum.usmeshka_groovy.domain.models.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoRepositoryImpl: VideoRepository {
    override suspend fun getVideos(): Flow<List<Video>> = flow {
        emit(listOf(
            Video(
                "Как правильно чистить зубы?",
                "c3eead665c0dadefed53302913c03e20",
                "Детское радио"
            ),
            Video(
                "СТОМАТОЛОГ показывает как ПРАВИЛЬНО ЧИСТИТЬ ЗУБЫ!",
                "1617a2cbd3042a1e1f4be67ef75b364b",
                "Алексей Смирнов | Хирург-стоматолог"
            ),
            Video(
                "Электрическая зубная щетка - эффективность, функционал, безопасность. Как чистить зубы правильно?",
                "257d1d9ff4a54c8958914af921399897",
                "Клуб DNS"
            ),
        ))
    }
}