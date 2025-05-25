package com.ru.practicum.usmeshka_groovy.ui.parent_boughts_fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.usmeshka_groovy.databinding.ParentShopFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ParentShopViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ProfileViewModel
import com.ru.practicum.usmeshka_groovy.ui.parent_shop.ParentShopFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class ParentShopAccountFragment : Fragment() {
    private var _binding: ParentShopFragmentBinding? = null
    private val binding get() = _binding!!
    private val vm: ProfileViewModel by viewModel()


    private var purchasesViewAdapter: PurchasesViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ParentShopFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        purchasesViewAdapter = PurchasesViewAdapter{
            PurchaseSummaryDialog(it).show( parentFragmentManager, null)
        }
        binding.purchasesRecyclerView.adapter = purchasesViewAdapter
        binding.purchasesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        vm.personalLiveData.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                binding.name.text = "${result.surname} ${result.name} ${result.patronymic}"
                vm.getPurchases()
            }
        }

        vm.setPersonalInfo()

        binding.cleanHistory.setOnClickListener {
            showToast("В ТЗ такого не было) Так что она просто украшает экран)")
        }

        vm.purchasesLiveData.observe(viewLifecycleOwner) { result ->
            Log.d("Got purchases", result.toString())
            purchasesViewAdapter?.setPurchases(result)
        }


    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        fun newInstance() = ParentShopAccountFragment().apply {}
    }
}