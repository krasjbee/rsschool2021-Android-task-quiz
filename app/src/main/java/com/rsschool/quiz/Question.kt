package com.rsschool.quiz

import java.io.Serializable

data class Question(
    val questionText: String,
    val answers: List<String>,
    val correctAnswerNumber: Int
) : Serializable {
    companion object {
        val EMPTY: Question = Question("", emptyList(), 0)
    }
}

