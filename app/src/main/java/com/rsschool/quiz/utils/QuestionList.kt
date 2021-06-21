package com.rsschool.quiz.utils

import com.rsschool.quiz.model.Question

object QuestionList {
    val questions = listOf(
        Question(
            "What is Android",
            listOf(
                "Operating system",
                "Just a robot",
                "Some word",
                "TRUE OVERLORD OF THIS WORLD",
                "I don't know"
            ),
            0
        ),
        Question(
            "What programming language is preferred for android development?",
            listOf("Java", "Kotlin", "Objective-C", "C++", "I don't know"),
            1
        ),
        Question(
            "What version number of android is last stable?",
            listOf("10", "11", "12", "1337", "I don't know"),
            1
        ),
        Question(
            "What IDE do android developers use?",
            listOf("VS code", "Vim", "Sublime", "Android Studio", "I don't know"),
            3
        ),
        Question(
            "Is android opensource?",
            listOf("Opensause", "Yes", "No", "I'm tired of your questions", "I don't know"),
            1
        ),
        Question(
            "How many answers do you see right now? ",
            listOf("1", "2", "3", "i cannot count to so big numbers", "5"),
            5
        )
    )
}