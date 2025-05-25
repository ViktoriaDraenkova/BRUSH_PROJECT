package com.ru.practicum.usmeshka_groovy.ui.parent_shop

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ru.practicum.usmeshka_groovy.ui.cart.CartFragment
import com.ru.practicum.usmeshka_groovy.ui.parent_boughts_fragment.ParentShopAccountFragment
import com.ru.practicum.usmeshka_groovy.ui.products_list.ProductsListFragment

class ViewPagerAdapterForShop(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> ProductsListFragment.newInstance()
            1 -> CartFragment.newInstance()
            else -> ParentShopAccountFragment.newInstance()
        }
    }
}