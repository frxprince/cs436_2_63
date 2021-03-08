package com.drpaween.primeservice

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat


class PrimeIntentService : IntentService("PrimeIntentService") {
var intent_progress=Intent()
    override fun onHandleIntent(intent: Intent?) {
 when(intent?.action){
     "start"->{ handleActionStart(intent.getLongExtra("k",0))}
 }
        }
    fun handleActionStart(k:Long) {
 intent_progress.setAction("com.drpaween.primeservice.progress")
        var result=primeK(k); Log.v("The answer is",result.toString())
var i=Intent(this,MainActivity::class.java)
 i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
 i.putExtra("result",result)
 ContextCompat.startActivity(this,i,null)
 intent_progress.putExtra("mode","result")
 intent_progress.putExtra("result",result)
        sendBroadcast(intent_progress)
    }
        fun isPrime(x:Long):Boolean{
            for(i in 2..x-1)
                if((x%i)==0L)
                    return false
            return true            }
        fun primeK(x:Long):Long{
            var c=0L;var i=3L
            while(true){
                if(isPrime(i)){
   intent_progress.putExtra("progress",Math.round((c/x.toFloat())*100) as Int)
   intent_progress.putExtra("mode","progress")
                    sendBroadcast(intent_progress)
                    c++; if(c==x)return i
                };i++
            };return 0 }
    }
