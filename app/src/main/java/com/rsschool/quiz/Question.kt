package com.rsschool.quiz

import java.io.Serializable

data class Question(
    val questionText: String,
    val answers: List<String>,
    val correctAnswerNumber: Int //Todo check if string is suitable
) : Serializable {
    companion object {
        public val EMPTY: Question = Question("", emptyList(), 0)
    }
}

