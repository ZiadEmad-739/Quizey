package com.ziad_emad_739.quizey.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ziad_emad_739.quizey.data.Object
import com.ziad_emad_739.quizey.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playNow()

    }

    private fun playNow() {
        binding.PlayNow.setOnClickListener {
            val name = binding.Name.text.toString().trim()
            if (name.isNotBlank()) {
                val intent = Intent(this@SignUp, MainActivity::class.java)
                intent.putExtra(Object.intentName,name)
                startActivity(intent)
                finish()
            }else{
                binding.Name.error = "Name is empty"
            }
        }
    }

}