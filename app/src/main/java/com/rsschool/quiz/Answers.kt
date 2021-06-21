package com.rsschool.quiz

import android.util.Log

object Answers : AnswerAccumulator {

    private val userAnswerMap: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    override fun addAnswerToMap(questionIndex: Int, viewId: Int, answerIndex: Int) {
        userAnswerMap[questionIndex] = viewId to answerIndex
    }


    override fun getPoints(): Int {
        var sumOfPoints = 0
//        userAnswerMap.forEach {
//            val rightAnswer = QuestionList.questions[it.key].correctAnswerNumber
//            val answerIndex = it.value.second
//            if (rightAnswer==answerIndex) sumOfPoints+=10
//        }
//        return sumOfPoints

        for (key in userAnswerMap.keys) {
            val rightAnswer = QuestionList.questions[key].correctAnswerNumber
            val answerIndex = userAnswerMap[key]?.second
            if (rightAnswer == answerIndex) sumOfPoints += 10
        }
        return sumOfPoints
    }

    override fun resetAnswers() {
        userAnswerMap.clear()
    }

    override fun getAnswers(): String {
        val answersStingBuilder = StringBuilder()
        for (key in userAnswerMap.keys) {
            val question = QuestionList.questions[key].questionText
            val userAnswer = QuestionList.questions[key].answers[userAnswerMap[key]?.second!!]
            answersStingBuilder.append(key + 1).append(") ").appendLine(question)
                .appendLine(userAnswer)
        }
        return answersStingBuilder.toString()
    }

    override fun getPossiblePoints() = QuestionList.questions.size * 10

    override fun getSelectedAnswerId(position: Int) = userAnswerMap[position]?.first ?: -1

    override fun printMap() {
        Log.d("qwe", "printMap: ${userAnswerMap.toString()} ")
    }
}