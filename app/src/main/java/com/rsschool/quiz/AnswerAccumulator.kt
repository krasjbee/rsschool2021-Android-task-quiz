package com.rsschool.quiz

interface AnswerAccumulator {
    fun addAnswerToMap(questionIndex:Int,viewId:Int,isCorrect:Boolean)
    fun addAnswerToMap(questionIndex:Int,viewId:Int)
    fun getPoints():Int
    fun getPossiblePoints():Int
    fun getSelectedAnswerId(position:Int):Int
    //fixme delete
    fun printMap()
    fun resetAnswers()
}