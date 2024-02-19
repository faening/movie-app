package com.github.faening.movieapp.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Pair<Fragment, Int>>()

    fun addFragment(fragment: Fragment, titleResId: Int) {
        fragments.add(Pair(fragment, titleResId))
    }

    fun getTitleResId(position: Int): Int {
        return fragments[position].second
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].first
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

}
