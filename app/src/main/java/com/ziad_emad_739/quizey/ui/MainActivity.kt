package com.ziad_emad_739.quizey.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.ziad_emad_739.quizey.R
import com.ziad_emad_739.quizey.data.Object
import com.ziad_emad_739.quizey.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        putName()
        goTo()
        textViewToolbar()

    }

     private fun textViewToolbar(){
         binding.myToolbar.TextToolbar.text = "Categories"
     }

     private fun putName(){
         val name = intent.getStringExtra(Object.intentName)
         binding.Hello.text = "Hello $name!"
     }

     private fun goTo() {
         val contents = arrayOf(binding.History, binding.Countries, binding.Math, binding.Football)
         for (content in contents) {
             content.setOnClickListener {
                 val titleContent = content.text.toString()
                 val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                 intent.putExtra(Object.intentContent, titleContent)
                 startActivity(intent)
             }
         }
     }

     private fun myAlertDialog(){
         val myAlert = LayoutInflater.from(this@MainActivity).inflate(R.layout.my_alert_dialog,null)
         val myAlertBuilder = AlertDialog.Builder(this).setView(myAlert)
         val myAlertInstance = myAlertBuilder.show()
         myAlertInstance.window?.setBackgroundDrawableResource(android.R.color.transparent)

         myAlert.findViewById<TextView>(R.id.AlertDialogueText).text = "Don't leave us"
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