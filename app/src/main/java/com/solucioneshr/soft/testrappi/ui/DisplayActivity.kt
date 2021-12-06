package com.solucioneshr.soft.testrappi.ui

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.solucioneshr.soft.testrappi.R
import com.solucioneshr.soft.testrappi.data.Movie
import com.solucioneshr.soft.testrappi.viewmodel.FunViewModels
import com.solucioneshr.soft.testrappi.viewmodel.TabsPagerAdapter
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class DisplayActivity : AppCompatActivity() {
    private lateinit var tab_layout: TabLayout
    private lateinit var  tabs_viewpager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        tab_layout = findViewById(R.id.tab_layout)
        tabs_viewpager = findViewById(R.id.tabs_viewpager)

        // Tabs Customization
        tab_layout.setSelectedTabIndicatorColor(Color.rgb(13,37, 63))
        tab_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_secondary))
        tab_layout.tabTextColors = ContextCompat.getColorStateList(this, R.color.white)

        // Set different Text Color for Tabs for when are selected or not
        //tab_layout.setTabTextColors(R.color.normalTabTextColor, R.color.selectedTabTextColor)

        // Number Of Tabs
        val numberOfTabs = 2

        // Set Tabs in the center
        //tab_layout.tabGravity = TabLayout.GRAVITY_CENTER

        // Show all Tabs in screen
        tab_layout.tabMode = TabLayout.MODE_FIXED

        // Scroll to see all Tabs
        //tab_layout.tabMode = TabLayout.MODE_SCROLLABLE

        // Set Tab icons next to the text, instead of above the text
        tab_layout.isInlineLabel = true

        // Set the ViewPager Adapter
        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        tabs_viewpager.adapter = adapter

        // Enable Swipe
        tabs_viewpager.isUserInputEnabled = true

        // Link the TabLayout and the ViewPager2 together and Set Text & Icons
        TabLayoutMediator(tab_layout, tabs_viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Peliculas"
                    tab.setIcon(R.drawable.ic_baseline_local_movies_24)
                }
                1 -> {
                    tab.text = "Series"
                    tab.setIcon(R.drawable.ic_baseline_live_tv_24)

                }

            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()


        setCustomTabTitles()

    }

    private fun setCustomTabTitles() {
        val vg = tab_layout.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount

        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup

            val tabChildCount = vgTab.childCount

            for (i in 0 until tabChildCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {

                    // Change Font and Size
                    tabViewChild.typeface = Typeface.DEFAULT_BOLD
//                    val font = ResourcesCompat.getFont(this, R.font.myFont)
//                    tabViewChild.typeface = font
//                    tabViewChild.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
                }
            }
        }
    }

}