package com.ru.practicum.usmeshka_groovy.ui.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.practicum.usmeshka_groovy.databinding.ProductDetailsFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Product
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ProductDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : Fragment() {

    private var _binding: ProductDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val vm: ProductDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ProductDetailsFragmentArgs by navArgs()
        val productJson = args.product
        val product = Gson().fromJson(productJson, Product::class.java)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.productName.text = product.title

        Glide.with(requireContext())
            .load(product.imageUrl)
            .into(binding.productImg)

        binding.price.text =
            ((product.price - product.sale).toFloat() / 100).toString() + "p/" + "${product.unitName}"

        binding.buttonAddToCart.setOnClickListener {
            vm.increaseInCart(product)
        }

        binding.buttonAdd.setOnClickListener {
            vm.increaseInCart(product)
        }

        binding.buttonRemove.setOnClickListener {
            vm.decreaseInCart(product)
        }

        vm.init(product)

        vm.getCountInCartLiveData().observe(viewLifecycleOwner) { count ->
            if (count <= 0) {
                binding.buttonAddToCart.visibility = View.VISIBLE
                binding.addDeleteProduct.visibility = View.GONE
            } else {
                binding.buttonAddToCart.visibility = View.GONE
                binding.addDeleteProduct.visibility = View.VISIBLE
            }

            binding.counter.text = count.toString()
        }
    }
}