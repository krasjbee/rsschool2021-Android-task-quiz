package com.rsschool.quiz

import android.os.Bundle
import android.util.Log
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
            orientation = ViewPager2.ORIENTATION_HORIZONTAL //FIXME delete if it's unnecessary
            isUserInputEnabled = false
        }
    }

    override fun toNextFragment() {
        Log.d("qwe", "toPrevFragment:${pager.currentItem} ")
        Log.d("qwe", "toNextFragment: ${StatusBarColors.list[(pager.currentItem + 1).rem(10)]}")
        pager.setCurrentItem(pager.currentItem + 1, false)
        window.statusBarColor = StatusBarColors.list[(pager.currentItem + 1).rem(10)]
    }

    override fun toPrevFragment() {
        Log.d("qwe", "toPrevFragment:${pager.currentItem} ")
        pager.setCurrentItem(pager.currentItem - 1, false)
        window.statusBarColor = StatusBarColors.list[(pager.currentItem).rem(10)]
    }

    override fun toStartFragment() {

        pager.setCurrentItem(0, false)
        pager.adapter = PagerAdapter(this)
    }
}