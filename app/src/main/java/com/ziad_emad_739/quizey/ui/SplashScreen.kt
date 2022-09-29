package com.ziad_emad_739.quizey.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ziad_emad_739.quizey.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doSplashScreen()
    }

    private fun doSplashScreen(){
        Handler().postDelayed({
          val intent = Intent(this@SplashScreen, SignUp::class.java)
          startActivity(intent)
          finish()
        },2000)
    }

}