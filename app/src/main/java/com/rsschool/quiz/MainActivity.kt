package com.rsschool.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Router {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        pager = binding.pager
        setupPager()
    }

    private fun setupPager() {
        pager.apply {
            adapter = PagerAdapter(this@MainActivity)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = false
        }
    }

    //i didn't manage to make it normal, so i hardcoded it
    private fun setStatusBarColor(currentItem: Int) {
        when (currentItem) {
            0 -> window.statusBarColor = resources.getColor(R.color.deep_orange_100_dark)
            1 -> window.statusBarColor = resources.getColor(R.color.yellow_100_dark)
            2 -> window.statusBarColor = resources.getColor(R.color.purple_200_dark)
            3 -> window.statusBarColor = resources.getColor(R.color.blue_200_dark)
            4 -> window.statusBarColor = resources.getColor(R.color.gray_400_dark)
            5 -> window.statusBarColor = resources.getColor(R.color.blue_400_dark)
            else -> window.statusBarColor = resources.getColor(R.color.gray_400)
        }
    }

    override fun toNextFragment() {
        pager.setCurrentItem(pager.currentItem + 1, false)
        setStatusBarColor(pager.currentItem)
    }

    override fun toPrevFragment() {
        pager.setCurrentItem(pager.currentItem - 1, false)
        setStatusBarColor(pager.currentItem)
    }

    override fun toStartFragment() {
        pager.setCurrentItem(0, false)
        pager.adapter = PagerAdapter(this)
    }
}