package com.rsschool.quiz

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rsschool.quiz.fragments.QuestionFragment
import com.rsschool.quiz.fragments.ShareFragment
import com.rsschool.quiz.utils.QuestionList

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    //+1 is for share fragment
    override fun getItemCount(): Int = QuestionList.questions.size + 1

    override fun createFragment(position: Int): Fragment {

        return if (position <= QuestionList.questions.lastIndex) {
            QuestionFragment.getInstance(QuestionList.questions[position], position)
        } else {
            ShareFragment()
        }
    }
}
