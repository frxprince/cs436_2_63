package com.example.mypreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text1=findViewById<EditText>(R.id.edittext1)
        var text2=findViewById<EditText>(R.id.edittext2)
        var BtnLoad=findViewById<Button>(R.id.buttonLoad)
        var BtnSave=findViewById<Button>(R.id.buttonSave)
        var BtnExit=findViewById<Button>(R.id.buttonExit)

     BtnLoad.setOnClickListener {
         var preference_data=getSharedPreferences("my_config", Context.MODE_PRIVATE)
         var data=preference_data.all
         text1.setText(data.get("data1")?.toString()?:"No data")
         text2.setText(preference_data.getString("data2","No data2"))
     }

     BtnSave.setOnClickListener {
         var preference_data=getSharedPreferences("my_config", Context.MODE_PRIVATE)
            preference_data.edit {
                putString("data1",text1.text.toString())
                putString("data2",text2.text.toString())
                commit()
            }

     }
        BtnExit.setOnClickListener { finish() }
    }
}