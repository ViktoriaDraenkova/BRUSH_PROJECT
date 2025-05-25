package com.ru.practicum.usmeshka_groovy.di

import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.AnalyticsViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ArticlesViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.AuthorisationViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.CartViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.NewsSalesViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ParentShopViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.PersonalAccountViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ProductDetailsViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ProductsListFragmentViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ProfileViewModel
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
        RegistrationViewModel(get(), get())
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

    viewModel {
        NewsSalesViewModel(get(), get())
    }
    viewModel {
        AuthorisationViewModel(get())
    }
    viewModel {
        CartViewModel(get(), get(), get())
    }
    viewModel {
        ParentShopViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get())
    }
    viewModel {
        ProfileViewModel(get(), get())
    }
    viewModel {
        ProductsListFragmentViewModel(get(), get())
    }
}