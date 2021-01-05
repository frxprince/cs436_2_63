package com.example.unitconversion2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var inputText=findViewById<EditText>(R.id.editTextNumberDecimal)
        var isCm=findViewById<RadioButton>(R.id.radioButton)
        var isInch=findViewById<RadioButton>(R.id.radioButton2)
        var calBtn=findViewById<Button>(R.id.button)
        var outputText=findViewById<TextView>(R.id.textView)

    calBtn.setOnClickListener {
        if (isCm.isChecked){
            outputText.text="${inputText.text.toString().toDouble()/2.54} inch"
        }else{
            outputText.text="${inputText.text.toString().toDouble()*2.54} cm"
        }

    }

    }
}