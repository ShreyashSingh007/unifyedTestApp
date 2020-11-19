package com.shreyash.testapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.shreyash.testapp.R
import com.shreyash.testapp.adapters.HomeFragmentPageAdapter
import com.shreyash.testapp.helpers.Constants
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    /**
     * Function to setup viewpager2
     * Swipe to shift to next page is disabled to enable better recyclerView scrolling experience on TabOne and TabTwo Fragments
     */

    private fun setUpViewPager() {
        val tabLayout = tabLayoutHomeFragment
        val viewPager = viewPagerHomeFragment

        val homeFragAdapter = HomeFragmentPageAdapter(this)
        viewPager.adapter = homeFragAdapter

        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = Constants.HOME_TAB_LAYOUT_NAME[position]
        }.attach()
    }
}