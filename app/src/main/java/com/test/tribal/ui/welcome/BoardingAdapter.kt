package com.test.tribal.ui.welcome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.tribal.models.Customization
import com.test.tribal.ui.welcome.fragments.BoardingFragment

class BoardingAdapter(fragmentActivity : FragmentActivity,
                      private val listCustomization : List<Customization>) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        BoardingFragment.newInstance(),
        BoardingFragment.newInstance(),
        BoardingFragment.newInstance()
    )

    override fun getItemCount(): Int =
        fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments[position].apply {
            setImage(this@BoardingAdapter.listCustomization[position].image)
            setBackground(this@BoardingAdapter.listCustomization[position].background)
            setScalable(this@BoardingAdapter.listCustomization[position].isScalable)
        }

    fun getFragments() = fragments


}