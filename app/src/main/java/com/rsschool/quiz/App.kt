package com.rsschool.quiz

import android.app.Application
import android.util.Log

class App:Application(),AnswerAccumulator {

    private val userAnswerMap: MutableMap<Int, Pair<Int, Boolean>> = mutableMapOf()

    override fun addAnswerToMap(questionIndex: Int, viewId: Int, isCorrect: Boolean) {
        userAnswerMap[questionIndex] = viewId to isCorrect
    }

    override fun addAnswerToMap(questionIndex: Int, viewId: Int) {
        userAnswerMap[questionIndex] = viewId to true
    }

    override fun getPoints(): Int {
        val numberOfCorrectAnswers = userAnswerMap.values.count { it.second }
        return numberOfCorrectAnswers * 10
    }

    override fun getPossiblePoints() = QuestionList.questions.size * 10

    override fun getSelectedAnswerId(position: Int) = userAnswerMap[position]?.first ?: -1

    override fun printMap() {
        Log.d("qwe", "printMap: ${userAnswerMap.toString()} ")
    }
}