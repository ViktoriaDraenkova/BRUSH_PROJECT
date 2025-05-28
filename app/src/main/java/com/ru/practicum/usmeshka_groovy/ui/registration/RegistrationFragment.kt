package com.ru.practicum.usmeshka_groovy.ui.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.RegistrationFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.User
import com.ru.practicum.usmeshka_groovy.presentation.states.AuthRegState
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.RegistrationViewModel
import com.ru.practicum.usmeshka_groovy.ui.authorisation.AuthorisationFragmentDirections
import com.ru.practicum.usmeshka_groovy.ui.root.RootActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {
    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModel()
    private var progress = 20
    private var user = User()

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

        viewModel.authStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthRegState.Loading -> {}
                is AuthRegState.Authenticated -> {
                    (activity as RootActivity).switchNavBar(state.user.isChild)
                    if (state.user.isChild) {
                        findNavController().navigate(
                            RegistrationFragmentDirections.actionRegistrationFragmentToStartFragment())
                    } else {
                        findNavController().navigate(
                            RegistrationFragmentDirections.actionRegistrationFragmentToInfoFragment())
                    }
                }
                is AuthRegState.Error -> showToast(state.message)
                is AuthRegState.Filling -> {}
            }
        }

        arrayOf(
            binding.nextBtn20,
            binding.nextBtn40,
            binding.nextBtn60,
            binding.nextBtn80,
            binding.nextBtn100
        ).forEach { button -> button.setOnClickListener { onNextClick(button) } }
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
                    user.isChild = selectedId == 0
                }
                binding.regNameSurname.visibility = View.VISIBLE
                binding.mark2.visibility = View.VISIBLE
            }

            binding.nextBtn40.id -> {
                user.name = binding.name.text.toString()
                user.surname = binding.surname.text.toString()
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

                if(user.isChild){
                    viewModel.saveRegistration(true)
                    viewModel.register(binding.emailEt.text.toString(), binding.passwordEt.text.toString(), user)
                }
                else{
                    binding.regSink.visibility = View.VISIBLE
                    binding.regEmail.visibility = View.GONE
                }

            }
            binding.nextBtn100.id ->{
                user.childId = binding.synkKey.text.toString()  // TODO: check user exist, maybe confirm from child account or show QR
                viewModel.saveRegistration(true)
                viewModel.register(binding.emailEt.text.toString(), binding.passwordEt.text.toString(), user)
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

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}