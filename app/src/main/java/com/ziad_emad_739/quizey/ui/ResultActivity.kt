package com.ziad_emad_739.quizey.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ziad_emad_739.quizey.data.Object
import com.ziad_emad_739.quizey.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResult()
        setLoserOrWinner()
        tryAgain()
        textToolbar()
        backToolbar()

    }

    private fun setResult(){
        val r = intent.getIntExtra(Object.result,0)
        val n = intent.getIntExtra(Object.numQuestion,0)
        binding.textResult.text = "$r out of $n Questions"
        binding.Result.text = "$r"
    }

    private fun setLoserOrWinner(){
        val r = intent.getIntExtra(Object.result,0)
        if (r > 5){
            binding.winnerOrLoser.text = "Congratulations"
            binding.winnerOrLoser.setTextColor(Color.YELLOW)
        }else{
            binding.winnerOrLoser.text = "Sorry, You Lose"
            binding.winnerOrLoser.setTextColor(Color.RED)
        }
    }

    private fun tryAgain(){
        binding.tryAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity,QuestionsActivity::class.java)
            intent.putExtra(Object.intentContent,Object.current)
            startActivity(intent)
            finish()
        }
    }

    private fun textToolbar(){
        binding.myToolbar.TextToolbar.text = "Result"
    }

    private fun backToolbar(){
        binding.myToolbar.backToolbar.setOnClickListener {
            Object.current = ""
            finish()
        }
    }

}