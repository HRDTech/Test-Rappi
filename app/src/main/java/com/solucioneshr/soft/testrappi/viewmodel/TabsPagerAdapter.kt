package com.solucioneshr.soft.testrappi.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solucioneshr.soft.testrappi.ui.MovieFragment
import com.solucioneshr.soft.testrappi.ui.TvFragment

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # Music Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "PeliculasFragment")
                val moviesFragment = MovieFragment()
                moviesFragment.arguments = bundle
                return moviesFragment
            }
            1 -> {
                // # Movies Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Series Fragment")
                val tvFragment = TvFragment()
                tvFragment.arguments = bundle
                return tvFragment
            }
            else -> return MovieFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}