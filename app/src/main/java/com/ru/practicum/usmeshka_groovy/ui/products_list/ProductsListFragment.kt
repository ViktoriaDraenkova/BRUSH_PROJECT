package com.ru.practicum.usmeshka_groovy.ui.products_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.ProductsListFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.states.ProductsScreenState
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ProductsListFragmentViewModel
import com.ru.practicum.usmeshka_groovy.ui.education.ViewPagerAdapter
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.VideosFragment
import com.ru.practicum.usmeshka_groovy.ui.parent_shop.ParentShopFragment
import com.ru.practicum.usmeshka_groovy.ui.parent_shop.ParentShopFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsListFragment : Fragment() {
    private var _binding: ProductsListFragmentBinding? = null
    private val binding get() = _binding!!
    private val vm: ProductsListFragmentViewModel by viewModel()
    private var productsViewAdapter: ProductsViewAdapter? = null
    private lateinit var tabMediator: TabLayoutMediator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsViewAdapter = ProductsViewAdapter(
            { vm.increaseInCart(it) }, { vm.decreaseInCart(it) },
            {
                val productJson = Gson().toJson(it) // Сериализация объекта в JSON
                val action =
                    ParentShopFragmentDirections.actionParentShopFragmentToProductDetailsFragment(productJson)
                findNavController().navigate(action)
            }
        )

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productsViewAdapter
        }

        vm.getScreenLiveData().observe(viewLifecycleOwner) { screenState ->
            hideAll()
            when (screenState) {
                is ProductsScreenState.Content -> {
                    productsViewAdapter?.setProducts(
                        screenState.data,
                        vm.getCountInCart(screenState.data)
                    )
                    binding.recyclerView.visibility = View.VISIBLE
                }

                is ProductsScreenState.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is ProductsScreenState.Error -> {
                    binding.noInternetConnection.visibility = View.VISIBLE
                }
            }
        }

        vm.getProducts()
    }

    private fun hideAll() {
        binding.error.visibility = View.GONE
        binding.loading.visibility = View.GONE
        binding.noInternetConnection.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
    }

    companion object {
        fun newInstance() = ProductsListFragment().apply {}
    }
}