package com.rsschool.quiz

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(private val activity: FragmentActivity) : FragmentStateAdapter(activity) {
    //+1 is for share fragment
    override fun getItemCount(): Int = QuestionList.questions.size + 1

    @SuppressLint("ResourceAsColor")
    override fun createFragment(position: Int): Fragment {

        val currentQuestion: Question = try {
            QuestionList.questions[position]
            //catching if our array is not out of bounds (it never should for question fragments
        } catch (e: Exception) {
            Question.EMPTY
        }

        return when (position) {
            itemCount - 1 -> ShareFragment()
            else -> QuestionFragment.getInstance(currentQuestion, position).also {
                val indexOfTheme = position.rem(10)
                activity.setTheme(Themes.list[indexOfTheme])
//                activity.window.statusBarColor=StatusBarColors.list[indexOfTheme]
            }
        }
    }
}