package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Router, AnswerAccumulator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pager: ViewPager2
    private val userAnswerMap: MutableMap<Int, Pair<Int, Boolean>> = mutableMapOf()

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

    override fun addAnswerToMap(questionIndex: Int, viewId: Int, isCorrect: Boolean) {
        userAnswerMap[questionIndex] = viewId to isCorrect
    }

    override fun getPoints(): Int {
        val numberOfCorrectAnswers = userAnswerMap.values.count { it.second }
        return numberOfCorrectAnswers * 10
    }

    override fun getPossiblePoints() = QuestionList.questions.size * 10
}