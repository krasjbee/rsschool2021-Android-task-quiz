package com.rsschool.quiz

interface AnswerAccumulator {
    fun addAnswerToMap(questionIndex:Int,viewId:Int,isCorrect:Boolean)
    fun getPoints():Int
    fun getPossiblePoints():Int
}