package com.ziad_emad_739.quizey.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ziad_emad_739.quizey.R
import com.ziad_emad_739.quizey.data.Object
import com.ziad_emad_739.quizey.data.QuestionsViewModel
import com.ziad_emad_739.quizey.databinding.ActivityQuestionsBinding

class QuestionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionsBinding
    private lateinit var viewModel: QuestionsViewModel
    private var currQuestion = 0
    private var correctAnswer = 0
    private var selectedAnswer = 0
    private var finishResult = 0
    private var questionSize = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[QuestionsViewModel::class.java]

        Object.current = intent.getStringExtra(Object.intentContent).toString()

        getContent()
        setData()
        submitNextApply()
        selectedOption()
        backToolbar()
        textToolbar()

    }

    private fun backToolbar(){
        binding.myToolbar.backToolbar.setOnClickListener {
            myAlertDialog()
        }
    }

    private fun textToolbar(){
        val title = intent.getStringExtra(Object.intentContent)
        binding.myToolbar.TextToolbar.text = title
    }

    private fun getContent(){
        val title = intent.getStringExtra(Object.intentContent).toString()
        viewModel.getContent(title)
    }

    private fun setData(){
        viewModel.getQuestionsSize()
        viewModel.mutableLiveDataQuestionsSize.observe(this, Observer {
            questionSize = it
        })

        viewModel.getQuestionData()
        viewModel.mutableLiveDataQuestion.observe(this, Observer {
            binding.numOfQuestion.text = it.id.toString() + "/" + questionSize
            binding.TextViewQuestion.text = it.questionText
            binding.imageQuestion.setImageResource(it.image)
            binding.Option1.text = it.option1
            binding.Option2.text = it.option2
            binding.Option3.text = it.option3
            binding.Option4.text = it.option4
            correctAnswer = it.answer
        })
    }

    private fun setDefaultColors(){
        val options = arrayOf(binding.Option1,binding.Option2,binding.Option3,binding.Option4)
        for (option in options){
            option.setBackgroundResource(R.drawable.background_non_selected_option)
            option.setTextColor(Color.WHITE)
        }
        binding.TextViewQuestion.setBackgroundResource(R.drawable.background_question)
        binding.submitNextFinish.text = "Submit"
    }

    private fun selectedOption(){
        val options = arrayOf(binding.Option1,binding.Option2,binding.Option3,binding.Option4)
        for (option in options) {
            option.setOnClickListener {
                setDefaultColors()
                option.setBackgroundResource(R.drawable.background_selected_option)
                option.setTextColor(Color.BLACK)
                selectedAnswer = options.indexOf(option) + 1
            }
        }
    }

    private fun submitNextApply(){
        binding.submitNextFinish.setOnClickListener {
            if (selectedAnswer != 0) {
                viewModel.getCurrQuestion()
                viewModel.mutableLiveDataCurrQuestion.observe(this, Observer {
                    currQuestion = it
                })
                checkAnswer()
                correctOrWrong()
                selectedOptionSecondTime()
                if (currQuestion == questionSize) {
                    binding.submitNextFinish.setOnClickListener {
                        val intent = Intent(this@QuestionsActivity,ResultActivity::class.java)
                        intent.putExtra(Object.result,finishResult)
                        intent.putExtra(Object.numQuestion,questionSize)
                        intent.putExtra(Object.intentContent,Object.current)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    binding.submitNextFinish.setOnClickListener {
                        viewModel.getNextQuestion()
                        setDefaultColors()
                        setData()
                        selectedAnswer = 0
                        selectedOption()
                        submitNextApply()
                    }
                }
            } else {
                binding.TextViewQuestion.setBackgroundResource(R.drawable.background_empty_answer)
                Toast.makeText(this@QuestionsActivity,"You should choose one Answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectedOptionSecondTime(){
        val options = arrayOf(binding.Option1,binding.Option2,binding.Option3,binding.Option4)
        for (option in options) {
            option.isClickable = false
        }
    }

    private fun checkAnswer(){
        if (selectedAnswer == correctAnswer){
            viewModel.setFinalResult()
        }
        viewModel.mutableLiveDataFinalResult.observe(this, Observer {
            finishResult = it
        })
    }

    private fun correctOrWrong(){
        val options = arrayOf(binding.Option1,binding.Option2,binding.Option3,binding.Option4)
        options[correctAnswer-1].setBackgroundResource(R.drawable.background_correct_answer)
        options[correctAnswer-1].setTextColor(Color.WHITE)
        if (selectedAnswer != correctAnswer){
            options[selectedAnswer-1].setBackgroundResource(R.drawable.background_wrong_answer)
            options[selectedAnswer-1].setTextColor(Color.WHITE)
        }
        changeTextSubmitNextFinish()
    }

    private fun changeTextSubmitNextFinish() {
        if (currQuestion != questionSize){
            binding.submitNextFinish.text = "Next"
        }else{
            binding.submitNextFinish.text = "Finish"
        }
    }

    private fun myAlertDialog(){
        val myAlert = LayoutInflater.from(this@QuestionsActivity).inflate(R.layout.my_alert_dialog,null)
        val myAlertBuilder = AlertDialog.Builder(this).setView(myAlert)
        val myAlertInstance = myAlertBuilder.show()
        myAlertInstance.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val continueButton = myAlert.findViewById<Button>(R.id.ContinueButton)
        val existButton = myAlert.findViewById<Button>(R.id.ExistButton)
        continueButton.setOnClickListener {
            myAlertInstance.dismiss()
        }
        existButton.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        myAlertDialog()
    }

}