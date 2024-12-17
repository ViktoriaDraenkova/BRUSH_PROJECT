package com.ru.practicum.usmeshka_groovy.ui.registration

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.RegistrationFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.PersonalAccountViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {
    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModel()
    private var progress = 20;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.whoUseRadio.setOnCheckedChangeListener { _, _ ->
            binding.nextBtn20.isEnabled = true
        }
        binding.notificatoinsAgreementRadio.setOnCheckedChangeListener { _, _ ->
            binding.nextBtn60.isEnabled = true
        }
        binding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val emailPattern = Regex(
                    "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
                )
                binding.nextBtn80.isEnabled = emailPattern.matches(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.nextBtn40.isEnabled = s?.isNotEmpty() ?: false
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        arrayOf(
            binding.nextBtn20,
            binding.nextBtn40,
            binding.nextBtn60,
            binding.nextBtn80,
        ).forEach { button -> button.setOnClickListener { onNextClick(button) } }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun onNextClick(v: View) {
        setButtonDisabled()
        hideAllSteps()
        hideAllMarks()
        progress += 20
        binding.percentCompleted.text = "$progress%"
        when (v.id) {
            binding.nextBtn20.id -> {
                val selectedId = binding.whoUseRadio.checkedRadioButtonId
                if (selectedId != -1) {
                    val selectedRadioButton: RadioButton = view?.findViewById(selectedId)!!
                    viewModel.saveWhoUse(selectedId, selectedRadioButton.text.toString())
                }
                binding.regNameSurname.visibility = View.VISIBLE
                binding.mark2.visibility = View.VISIBLE
            }

            binding.nextBtn40.id -> {
                viewModel.saveNameSurname(
                    binding.name.text.toString(),
                    binding.surname.text.toString()
                )
                binding.regNotifyYesNo.visibility = View.VISIBLE
                binding.mark3.visibility = View.VISIBLE
            }

            binding.nextBtn60.id -> {
                val selectedId = binding.notificatoinsAgreementRadio.checkedRadioButtonId
                if (selectedId != -1) {
                    val selectedRadioButton: RadioButton = view?.findViewById(selectedId)!!
                    viewModel.saveNotificationsIsNeed(
                        selectedId,
                        selectedRadioButton.text.toString()
                    )
                }
                binding.regEmail.visibility = View.VISIBLE
                binding.mark4.visibility = View.VISIBLE
            }

            binding.nextBtn80.id -> {
                viewModel.saveEmail(binding.emailEt.text.toString())
                findNavController().navigate(R.id.action_registrationFragment_to_startFragment)
                viewModel.saveRegistration(true)
                // TODO: save all and open app
            }
        }
    }

    fun setButtonDisabled() {
        arrayOf(
            binding.nextBtn20,
            binding.nextBtn40,
            binding.nextBtn60,
            binding.nextBtn80,
        ).forEach { button -> button.isEnabled = false }
    }

    private fun hideAllSteps() {
        arrayOf(
            binding.regEmail,
            binding.regNotifyYesNo,
            binding.regNameSurname,
            binding.regWhoUse,
        ).forEach { it.visibility = View.GONE }
    }

    private fun hideAllMarks() {
        arrayOf(
            binding.mark1,
            binding.mark2,
            binding.mark3,
            binding.mark4,
        ).forEach { it.visibility = View.GONE }
    }
}