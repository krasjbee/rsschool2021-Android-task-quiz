package com.rsschool.quiz.model

import java.io.Serializable

data class Question(
    val questionText: String,
    val answers: List<String>,
    val correctAnswerNumber: Int
) : Serializable
