package com.ru.practicum.usmeshka_groovy.ui.settings

import android.R.attr.label
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.SettingsFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.SettingsViewModel
import com.ru.practicum.usmeshka_groovy.ui.root.RootActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.switchAccount.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_parentImportantFragment)
            (activity as RootActivity).switchNavBar()
        }

        viewModel.getuidLivedata().observe(viewLifecycleOwner) { uid ->
            Log.d("uid", uid)
            showDiaog(uid)
        }

        binding.acccount.setOnClickListener {
            viewModel.getUid()
            Log.d("clicked", "AAAAAA")

        }
    }

    private fun showDiaog(code: String) {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_share_child_account, null)
        val myDialog = Dialog(requireContext())
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        myDialog.show()

        dialogBinding.findViewById<TextView>(R.id.synk_code_dialog).text = code

        dialogBinding.findViewById<Button>(
            R.id.copy_synkcode
        ).setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", code)
            clipboard.setPrimaryClip(clip)
        }
    }
}