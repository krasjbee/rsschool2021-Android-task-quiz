package com.rsschool.quiz

import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.utils.Themes

class MainActivity : AppCompatActivity(), Router {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pager: ViewPager2
    private val changeThemeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            setTheme(Themes.list[position % 10])
            val typedValue = TypedValue()
            theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
            window.statusBarColor = typedValue.data
        }
    }


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
            registerOnPageChangeCallback(changeThemeCallback)
        }
    }

    override fun toNextFragment() {
        pager.setCurrentItem(pager.currentItem + 1, false)
    }

    override fun toPrevFragment() {
        pager.setCurrentItem(pager.currentItem - 1, false)
    }

    override fun toStartFragment() {
        pager.setCurrentItem(0, false)
        pager.adapter = PagerAdapter(this)
    }
    //fixme check if unregister callback is needed
}