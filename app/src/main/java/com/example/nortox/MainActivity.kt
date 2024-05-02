package com.example.nortox

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eTPrompt = findViewById<EditText>(R.id.eTPrompt)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tVResult = findViewById<TextView>(R.id.tVResult)
        val showPrompt = findViewById<TextView>(R.id.sPrompt)


        btnSubmit.setOnClickListener {
            val context = applicationContext
            val toast = Toast.makeText(context, " Generating...", Toast.LENGTH_LONG)
            toast.show();

            val prompt = eTPrompt.text.toString()

            showPrompt.text = prompt

            val generativeModel = GenerativeModel(

                modelName = "gemini-pro",

                apiKey = "Here Goes Your Api Key"
            )

            try {
                runBlocking {
                    val response = generativeModel.generateContent(prompt)
                    tVResult.text = response.text
                }
            }
            catch (e: ResponseStoppedException){
                tVResult.text = e.toString()
            }
        }



    }
}