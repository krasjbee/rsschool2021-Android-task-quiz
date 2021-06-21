package com.rsschool.quiz

//i used another solution , but that was overengineering, so i've changed it's implementation and decided to keep interface
interface AnswerAccumulator {
    fun addAnswerToMap(questionIndex: Int, viewId: Int, answerIndex: Int)
    fun getPoints(): Int
    fun getPossiblePoints(): Int
    fun getSelectedAnswerId(position: Int): Int
    fun resetAnswers()
    fun getAnswers(): String
}