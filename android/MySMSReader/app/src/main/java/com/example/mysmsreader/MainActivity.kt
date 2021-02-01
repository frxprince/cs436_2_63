package com.example.mysmsreader

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS),123)
        }
        var Text1=findViewById<TextView>(R.id.textView)
        var receiverObj=object:SMSReceiver(){
            override fun showMessage(msg: String, phone: String) {
               Text1.text=" From: $phone\n Message:\n$msg     "
            }
        }
        registerReceiver(receiverObj, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))

    }

    abstract class SMSReceiver: BroadcastReceiver(){
         override fun onReceive(context: Context?, intent: Intent?) {
      var message=Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
             showMessage(message.displayMessageBody,message.displayOriginatingAddress)
         }
        abstract fun showMessage(msg:String,phone:String)

     }
}