package com.ru.practicum.usmeshka_groovy.ui.parent_shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.ShopAllFragsBinding

class ParentShopFragment : Fragment() {
    private var _binding: ShopAllFragsBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("AAAAAAAAAA_2", "22222222222")
        _binding = ShopAllFragsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initViewPager2() {
        val viewPager: ViewPager2 = binding.viewPager
        val adapter = ViewPagerAdapterForShop(childFragmentManager, viewLifecycleOwner.lifecycle)
        viewPager.adapter = adapter
        Log.d("AAAAAAAAAA_3", "3333333333")

        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            Log.d("position", position.toString())

            when (position) {

                0 -> tab.text = getString(R.string.products)
                1 -> tab.text = getString(R.string.cart)
                2 -> tab.text = getString(R.string.personal_account)
            }
        }
        tabMediator.attach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager2()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator.detach()
    }

    companion object {
        fun newInstance() = ParentShopFragment().apply {}
    }
}