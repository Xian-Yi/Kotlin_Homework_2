package com.example.lab5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// ViewPagerAdapter 繼承 FragmentStateAdapter 類別
class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {
        private const val NUM_PAGES = 3
    }

    // 回傳 Fragment 的數量
    override fun getItemCount(): Int = NUM_PAGES

    // 根據 position 位置回傳對應的 Fragment
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FirstFragment()    // 第一頁 Fragment
            1 -> SecondFragment()   // 第二頁 Fragment
            else -> ThirdFragment() // 第三頁 Fragment
        }
}
