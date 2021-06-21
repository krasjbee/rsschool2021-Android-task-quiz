package com.rsschool.quiz

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(private val activity: FragmentActivity) : FragmentStateAdapter(activity) {
    //todo check if it's memoryleak
    //+1 is for share fragment
    override fun getItemCount(): Int = QuestionList.questions.size + 1

    override fun createFragment(position: Int): Fragment {
        val currentQuestion: Question = try {
            QuestionList.questions[position]
        } catch (e: Exception) {
            Question.EMPTY
        }
        return when (position) {
            0 -> QuestionFragment.getInstance(
                currentQuestion,
                QuestionFragment.FIRST_QUESTION,
                position
            )
            QuestionList.questions.lastIndex -> QuestionFragment.getInstance(
                currentQuestion,
                QuestionFragment.LAST_QUESTION,
                position
            )
            itemCount - 1 -> ShareFragment()
            else -> QuestionFragment.getInstance(currentQuestion, position)
        }
    }
}