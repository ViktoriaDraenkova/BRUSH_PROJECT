package com.ru.practicum.usmeshka_groovy.presentation.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerFragmentViewModel : ViewModel() {
    private val _timerState = MutableStateFlow(Pair("03", "00"))
    val timerState = _timerState.asStateFlow()

    private val timer: CountDownTimer = createTimer()

    private fun createTimer(): CountDownTimer {
        return object : CountDownTimer(3 * 60 * 1_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerState.value =
                    Pair(getTimeMins(millisUntilFinished), getTimeSec(millisUntilFinished))
            }

            override fun onFinish() {
            }
        }
    }

    private fun getTimeSec(millis: Long): String {
        val seconds = millis / 1_000
        val sec: Long = seconds % 60
        return String.format("%02d", sec)
    }

    private fun getTimeMins(millis: Long): String {
        val seconds = millis / 1_000
        val mins = seconds / 60
        return String.format("%02d", mins)
    }

    fun onStartBtnClick() {
        timer.start()
    }
}









