package com.ziad_emad_739.quizey.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {

    private val objectOfCategories = Categories()
    private var questions = arrayOf<Question>()
    private var questionNum = 0
    private var currQuestion = 0
    private var finalResult = 0

    fun getContent(title : String){
        when (title) {
            Object.history -> {
                questions = objectOfCategories.history
            }
            Object.countries -> {
                questions = objectOfCategories.countries
            }
            Object.math -> {
                questions = objectOfCategories.math
            }
            Object.football -> {
                questions = objectOfCategories.football
            }
        }
    }

    val mutableLiveDataQuestion = MutableLiveData<Question>()
    private val mutableLiveDataQuestionNum = MutableLiveData<Int>()
    val mutableLiveDataQuestionsSize = MutableLiveData<Int>()
    val mutableLiveDataCurrQuestion = MutableLiveData<Int>()
    val mutableLiveDataFinalResult = MutableLiveData<Int>()

    fun getQuestionData(){
        mutableLiveDataQuestion.value = questions[questionNum]
    }

    fun getNextQuestion(){
        if (questionNum == questions.size){
            questionNum = -1
        }else {
            questionNum += 1
        }
        mutableLiveDataQuestionNum.value = questionNum
    }

    fun getQuestionsSize() {
        mutableLiveDataQuestionsSize.value = questions.size
    }

    fun getCurrQuestion(){
        currQuestion += 1
        mutableLiveDataCurrQuestion.value = currQuestion
    }

    fun setFinalResult(){
        finalResult += 1
        mutableLiveDataFinalResult.value = finalResult
    }

}