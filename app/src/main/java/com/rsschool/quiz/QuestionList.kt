package com.rsschool.quiz

object QuestionList {
    val questions = listOf<Question>(
        Question(
            "300?",
            listOf("programmista ", "traktorista"),
            1
        )//FIXME MAKE NORMAL QUESTION
        ,
        Question("qweqwe", listOf("qqq", "qwe", "wwww"), 3),
        Question("FFFFF", listOf("qqq", "qwe", "wwww", "qwe"), 3),
        Question("Qusestioqweqwerg", listOf("qwe,wwww", "qw"), 2)
    )
}