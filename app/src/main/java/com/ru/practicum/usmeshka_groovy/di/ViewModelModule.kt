package com.ru.practicum.usmeshka_groovy.di

import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.TimerFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TimerFragmentViewModel()
    }
}