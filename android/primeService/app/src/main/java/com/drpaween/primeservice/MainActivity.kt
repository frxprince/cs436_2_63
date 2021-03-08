package com.drpaween.primeservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var TextResult:TextView
    lateinit var TextProgress:TextView
   var receiver=object:MyReceiver(){
       override fun showProgress(progress: Int) {
          TextProgress.text=progress.toString()
       }

       override fun showResult(result: Long) {
           TextResult.text=result.toString()
       }

   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    var TextInput=findViewById<EditText>(R.id.editTextNumber)
    TextResult=findViewById<TextView>(R.id.textViewResult)
    TextProgress=findViewById<TextView>(R.id.textViewProgress)
    var BtnStart=findViewById<Button>(R.id.buttonStart)
    var BtnExit=findViewById<Button>(R.id.buttonExit)
    BtnStart.setOnClickListener {
        var i= Intent(this,PrimeIntentService::class.java)
        i.putExtra("k",TextInput.text.toString().toLong())
        i.setAction("start")
        startService(i)
    }
       registerReceiver(receiver, IntentFilter("com.drpaween.primeservice.progress"))
    }
    abstract class MyReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
     if(intent!!.getStringExtra("mode")=="progress")
         showProgress(intent!!.getIntExtra("progress",0))
     if(intent!!.getStringExtra("mode")=="result")
                showResult(intent!!.getLongExtra("result",0))
        }
     abstract fun showProgress(progress:Int)
     abstract fun showResult(result:Long)


    }

}