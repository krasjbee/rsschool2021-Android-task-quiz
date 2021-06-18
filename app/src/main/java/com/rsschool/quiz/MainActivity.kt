package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Router {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pager :ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        pager = binding.pager
        pager.apply {
            adapter = PagerAdapter(this@MainActivity)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL //FIXME delete if it's unnecessary
            isUserInputEnabled = false
        }
    }

    override fun nextFragment() {
        pager.currentItem += 1
    }

    override fun prevFragment() {
        pager.currentItem -= 1
    }

}