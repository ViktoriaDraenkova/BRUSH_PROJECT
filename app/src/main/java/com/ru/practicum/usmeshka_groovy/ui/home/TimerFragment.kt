package com.ru.practicum.usmeshka_groovy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.practicum.usmeshka_groovy.databinding.TimerFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.TimerFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimerFragment : Fragment() {
    private var binding: TimerFragmentBinding? = null
    private val viewModel: TimerFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TimerFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonStartCleaning?.setOnClickListener {
            viewModel.onStartBtnClick()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.timerState.collect { state ->
                binding?.timeMins?.text = state.first
                binding?.timeSec?.text = state.second
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}