package com.example.unitconversion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    var textInput=findViewById<EditText>(R.id.textInput)
    var ButtonInchToCm=findViewById<Button>(R.id.button)
    var textOutput=findViewById<TextView>(R.id.textView)
    var ButtonCmtoInch=findViewById<Button>(R.id.button2)
    var ButtonExit=findViewById<Button>(R.id.button3)
    ButtonInchToCm.setOnClickListener {
       // textOutput.text=textInput.text
        try {
            var inch=textInput?.text.toString().toDouble()?:0.0
            var cm=inch*2.54
            textOutput.text="$cm cm."
        } catch (e: NumberFormatException) {
            textOutput.text="Invalid input"
        }

    }

        ButtonCmtoInch.setOnClickListener {
            try {
                var cm=textInput?.text.toString().toDouble()?:0.0
                var inch=cm/2.54
                textOutput.text="$inch inch."
            } catch (e: NumberFormatException) {
                textOutput.text="Invalid input"
            }
        }

        ButtonExit.setOnClickListener { finish() }

    }
}