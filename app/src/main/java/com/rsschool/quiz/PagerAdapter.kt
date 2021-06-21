package com.rsschool.quiz

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    //+1 is for share fragment
    override fun getItemCount(): Int = QuestionList.questions.size//+1

    override fun createFragment(position: Int): Fragment {
        val currentQuestion = QuestionList.questions[position]
        return when (position) {
            0 -> QuestionFragment.getInstance(currentQuestion,QuestionFragment.FIRST_QUESTION,position)
            QuestionList.questions.lastIndex -> QuestionFragment.getInstance(currentQuestion,QuestionFragment.LAST_QUESTION,position)
            itemCount -> TODO("Set share fragment")
            else -> QuestionFragment.getInstance(currentQuestion,position) //Normal question fragment
        }
    }
}