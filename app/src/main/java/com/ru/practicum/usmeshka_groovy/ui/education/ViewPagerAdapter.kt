package com.ru.practicum.usmeshka_groovy.ui.education

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.ArticlesFragment
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.VideosFragment


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VideosFragment.newInstance()
            else -> ArticlesFragment.newInstance()
        }
    }
}
