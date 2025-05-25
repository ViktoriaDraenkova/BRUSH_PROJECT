package com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.InfoFragmentBinding

class InfoFragment  : Fragment() {
    private var _binding: InfoFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InfoFragmentBinding.inflate(inflater, container, false)
        return binding.root }

    private fun initViewPager2() {
        val viewPager: ViewPager2 = binding.viewPager
        val adapter = ViewPagerAdapterP(childFragmentManager, viewLifecycleOwner.lifecycle)
        viewPager.adapter = adapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.news)
                1 -> tab.text = getString(R.string.articles)
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
}