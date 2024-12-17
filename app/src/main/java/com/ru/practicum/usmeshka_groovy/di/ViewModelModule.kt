package com.ru.practicum.usmeshka_groovy.di

import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.AnalyticsViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ArticlesViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.PersonalAccountViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.RegistrationViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.TimerFragmentViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TimerFragmentViewModel(get())
    }
    viewModel {
        PersonalAccountViewModel(get(), get(), get())
    }
    viewModel {
        RegistrationViewModel(get())
    }
    viewModel {
        AnalyticsViewModel(get(), get())
    }
    viewModel {
        ArticlesViewModel(get())
    }
    viewModel {
        VideoViewModel(get())
    }
}