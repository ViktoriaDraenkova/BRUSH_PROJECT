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

        val needRegistration = !prefsManager.getRegistrationCompleted()
        Handler(Looper.myLooper()!!).postDelayed({
            if (!needRegistration) {
                findNavController().navigate(R.id.action_splashFragment_to_startFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_registrationFragment)
            }
        }, 2000)
        return  view
    }
}