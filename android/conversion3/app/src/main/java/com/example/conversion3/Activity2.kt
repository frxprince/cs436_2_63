package com.example.conversion3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText

class Activity2 : AppCompatActivity() {

    var finalResult:Double=0.0
    lateinit var inputText:EditText

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if(resultCode==Activity.RESULT_OK){
        if(requestCode==1234){
            var select_unit=data!!.getStringExtra("data")
            when(select_unit){
                "kb"-> finalResult=inputText.text.toString().toInt()/1000.0
                "mb"->finalResult=inputText.text.toString().toInt()/1000000.0
                "gb"->finalResult=inputText.text.toString().toInt()/1000000000.0
            }
//Log.v("answer",finalResult.toString())
            var activity4Intent=Intent(this,Activity4::class.java)
            activity4Intent.putExtra("answer",finalResult)
            startActivity(activity4Intent)

        }
    }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in,R.anim.slide_up)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in,R.anim.slide_up)
        setContentView(R.layout.activity_2)
        var animationBlink=AnimationUtils.loadAnimation(this,R.anim.blink )
        var animationRotate=AnimationUtils.loadAnimation(this,R.anim.rotate)
        var BtnSelect=findViewById<Button>(R.id.button)
        var BtnShow=findViewById<Button>(R.id.button2)
        BtnShow.startAnimation(animationRotate)
        BtnSelect.startAnimation(animationBlink)
        inputText=findViewById(R.id.editTextNumber)



        BtnSelect.setOnClickListener {
            var activity2Intent= Intent(this,Activity3::class.java)
            startActivityForResult(activity2Intent,1234)
        }
    }
}