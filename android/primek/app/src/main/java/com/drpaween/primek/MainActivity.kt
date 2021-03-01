package com.drpaween.primek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var BtnStart=findViewById<Button>(R.id.buttonStart)
        var BtnStop=findViewById<Button>(R.id.buttonStop)
        var InputText=findViewById<EditText>(R.id.editTextNumber)
        var TextProgress=findViewById<TextView>(R.id.textView)
        var TextResult=findViewById<TextView>(R.id.textView2)
        lateinit var worker:PrimeKThread
        BtnStart.setOnClickListener {
            worker=object:PrimeKThread(InputText.text.toString().toLong()){
                override fun ShowResult(result: Long) {
                    this@MainActivity.runOnUiThread(Runnable {TextResult.text=result.toString()  })
                }
                override fun ShowProgress(progress: Int) {
                    this@MainActivity.runOnUiThread(Runnable {TextProgress.text=progress.toString()+"%"  })
                }
            }
            worker.start()  }
        BtnStop.setOnClickListener { worker.running=false }
    }
     abstract class PrimeKThread(val k:Long):Thread(){
         var running=true
         override fun run() {
             super.run()
             val result=primeK(k)
             ShowResult(result)         }
         abstract fun ShowResult(result:Long);abstract fun ShowProgress(progress:Int)
         fun isPrime(x:Long):Boolean{
             for(i in 2..x-1)
                 if((x%i)==0L){return false}
             return true
         }

         fun primeK(x:Long):Long{
             var i=3L; var c=0L
             while(running){  if(isPrime(i)){  c++; ShowProgress(Math.round((c/x.toFloat())*100) )
                 if(c==x){ return i}
             }
                 i++    }
             return 0   }  }



}