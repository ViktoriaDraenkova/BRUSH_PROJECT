package com.ru.practicum.usmeshka_groovy.ui.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.StartFragmentBinding

class StartFragment : Fragment() {

    var binding: StartFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonStart?.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_timerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}