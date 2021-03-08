package com.drpaween.asyncprime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var TextInput=findViewById<EditText>(R.id.editTextNumber)
        var progressBar1=findViewById<ProgressBar>(R.id.progressBar)
        var Btn=findViewById<Button>(R.id.buttonStart)
        var TextResult=findViewById<TextView>(R.id.textView)
        progressBar1.max=100
        Btn.setOnClickListener {
            doAsync {
                fun isPrime(x:Long):Boolean{
                    for(i in 2..x-1)
                        if((x%i)==0L)
                            return false
                    return true            }
                fun primeK(x:Long):Long{
                    var c=0L;var i=3L
                    while(true){
                        if(isPrime(i)){
        uiThread { progressBar1.setProgress(Math.round((c/x.toFloat())*100) as Int) }
                            Log.v("debug",c.toString())
                            c++; if(c==x)return i
                        };i++
                    };return 0          }
                var result=primeK(TextInput.text.toString().toLong())
            uiThread { TextResult.text=result.toString() }
            }
        }
    }
}