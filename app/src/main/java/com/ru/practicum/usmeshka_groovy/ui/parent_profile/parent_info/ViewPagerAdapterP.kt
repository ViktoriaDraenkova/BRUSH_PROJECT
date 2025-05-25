package com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapterP (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        Log.d("aa", position.toString())
        return when (position) {
            0 -> NewsFragmentP.newInstance()
            else -> ArticlesFragmentP.newInstance()
        }
    }
}
