package com.ziad_emad_739.quizey.data

data class Question(
    val id : Int,
    val questionText : String,
    val image : Int,
    val option1 : String,
    val option2 : String,
    val option3 : String,
    val option4 : String,
    val answer : Int,
)
