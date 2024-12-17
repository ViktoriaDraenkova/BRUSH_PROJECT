package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.practicum.usmeshka_groovy.domain.education.video.VideoInteractor
import com.ru.practicum.usmeshka_groovy.domain.models.Video
import kotlinx.coroutines.launch

class VideoViewModel(private val videoInteractor: VideoInteractor) : ViewModel() {
    private val videosLiveData = MutableLiveData<List<Video>>()

    fun getVideosLiveData(): LiveData<List<Video>> = videosLiveData

    fun getVideos() {
        viewModelScope.launch {
            videoInteractor.getVideos().collect {
                videosLiveData.value = it
            }
        }
    }
}