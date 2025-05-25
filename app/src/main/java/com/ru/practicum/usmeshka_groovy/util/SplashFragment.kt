package com.ru.practicum.usmeshka_groovy.util

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.usmeshka_groovy.R
import com.ru.practicum.usmeshka_groovy.domain.prefs.PrefsManagerInteractor
import org.koin.android.ext.android.inject


class SplashFragment : Fragment() {

    private val prefsManager: PrefsManagerInteractor by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.splash_fragment, container, false)

        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAuthorisationFragment())
        }, 2000)
        return  view
    }
}