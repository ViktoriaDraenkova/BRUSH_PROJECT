package com.ru.practicum.usmeshka_groovy.ui.cart
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.practicum.usmeshka_groovy.databinding.CartFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.CartViewModel
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ProfileViewModel
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.VideosFragment
import com.ru.practicum.usmeshka_groovy.ui.parent_shop.ParentShopFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class CartFragment : Fragment() {
    private var _binding: CartFragmentBinding? = null
    private val binding get() = _binding!!
    private val vm: CartViewModel by viewModel()


    private var cartViewAdapter: CartProductsViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewAdapter = CartProductsViewAdapter(
            { vm.increaseInCart(it) }, { vm.decreaseInCart(it) },
            {
                val productJson = Gson().toJson(it)
                val action =
                    ParentShopFragmentDirections.actionParentShopFragmentToProductDetailsFragment(productJson)
                findNavController().navigate(action)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartViewAdapter
        }

        cartViewAdapter!!.setProducts(
            vm.getCart().map { it.first },
            vm.getCart().map { it.second }
        )

        binding.buyBtn.setOnClickListener {
            vm.buy {
                cartViewAdapter!!.clearProducts()
            }
        }
    }
    companion object {
        fun newInstance() = CartFragment().apply {}
    }
}