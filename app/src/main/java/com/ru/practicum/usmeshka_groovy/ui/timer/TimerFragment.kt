package com.ru.practicum.usmeshka_groovy.ui.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.TimerFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.TimerFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimerFragment : Fragment() {
    private var _binding: TimerFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TimerFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = TimerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeAllTeethEmpty()
        binding.buttonStartCleaning.setOnClickListener {
            viewModel.onStartBtnClick()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.timerState.collect { state ->
                binding.timeMins.text = state.first
                binding.timeSec.text = state.second
                cleaning(state)
            }
        }
        binding.durtyTooth.animate()?.rotation(15f)?.setDuration(1500)?.withEndAction {
            startRotationAnimation()

        }
    }

    private fun makeAllTeethEmpty() {
        binding.dLeftCleaning.visibility = View.GONE
        binding.topCleaning.visibility = View.GONE
        binding.dRightCleaning.visibility = View.GONE
        binding.leftCleaning.visibility = View.GONE
        binding.rightCleaning.visibility = View.GONE
        binding.downCleaning.visibility = View.GONE
    }

    fun cleaning(state: Pair<String, String>) {
        if (state == Pair("01", "59")) {
            binding.durtyTooth.setImageResource(R.drawable.stomic_dirty)
            makeAllTeethEmpty()
            binding.leftCleaning.visibility = View.VISIBLE
        }

        if (state == Pair("01", "40")) {
            makeAllTeethEmpty()
            binding.topCleaning.visibility = View.VISIBLE
        }
        if (state == Pair("01", "20")) {
            makeAllTeethEmpty()
            binding.rightCleaning.visibility = View.VISIBLE
        }
        if (state == Pair("01", "00")) {
            makeAllTeethEmpty()
            binding.dRightCleaning.visibility = View.VISIBLE
        }
        if (state == Pair("00", "40")) {
            makeAllTeethEmpty()
            binding.downCleaning.visibility = View.VISIBLE
        }
        if (state == Pair("00", "20")) {
            makeAllTeethEmpty()
            binding.dLeftCleaning.visibility = View.VISIBLE
        }

        if (state == Pair("00", "01")) {
            binding.durtyTooth.setImageResource(R.drawable.stomic_small)
            makeAllTeethEmpty()
        }
    }

    private fun startRotationAnimation() {
        // Поворот направо на 30 градусов
        binding.durtyTooth.animate()?.rotation(-15f)?.setDuration(3000)?.withEndAction {
            // Поворот налево на 30 градусов
            binding.durtyTooth.animate()?.rotation(15f) // Возвращаем в исходное положение
                ?.setDuration(3000)?.withEndAction {
                    // Запускаем анимацию снова
                    startRotationAnimation()
                }?.start()
        }?.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}