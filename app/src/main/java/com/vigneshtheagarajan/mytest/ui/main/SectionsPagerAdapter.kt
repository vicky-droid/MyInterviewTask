package com.vigneshtheagarajan.mytest.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vigneshtheagarajan.mytest.model.MainActivityModel


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    val tabNames: Array<String>,
    val data: MainActivityModel
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(data.foodList[position] )
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }

    override fun getCount(): Int {
        return tabNames.size
    }
}