package com.example.mysms

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

   if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
       ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.SEND_SMS),1234)
   }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_PHONE_STATE),1234)
        }


        var BtnSend=findViewById<Button>(R.id.buttonSend)
        var TxtPhoneNo=findViewById<EditText>(R.id.editTextNumber)
        var TxtMessage=findViewById<EditText>(R.id.editTextTextMultiLine)
        var TextResult=findViewById<TextView>(R.id.textView)

        var sentPI=PendingIntent.getBroadcast(this,0, Intent("SMS_SENT"),0)
        var deliveredPI=PendingIntent.getBroadcast(this,0,Intent("SMS_DELIVERED"),0)

        BtnSend.setOnClickListener {
         var sms=SmsManager.getDefault()
            sms.sendTextMessage(TxtPhoneNo.text.toString(),null,TxtMessage.text.toString(),sentPI,deliveredPI)
        }

var SMSsentObj=object:SMSsent() {
    override fun Show(message: String) {
        TextResult.text = "${TextResult.text} $message\n"

    }
}

var SMSdeliveredObj=object:SMSdelivered(){
    override fun Show(message: String) {
        TextResult.text="${TextResult.text} $message\n"
    }

}
   registerReceiver(SMSsentObj, IntentFilter("SMS_SENT"))
    registerReceiver(SMSdeliveredObj,IntentFilter("SMS_DELIVERED"))
        }



    abstract class SMSsent:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var ResultText=when(resultCode){
                Activity.RESULT_OK->"The SMS is sent"
                SmsManager.RESULT_ERROR_GENERIC_FAILURE->" Generic error"
                SmsManager.RESULT_ERROR_NO_SERVICE->"No service"
                SmsManager.RESULT_ERROR_RADIO_OFF->"Radio off"
                else->" I don't know"
            }
            Show(ResultText)
        }
        abstract fun Show(message:String)
    }

    abstract class SMSdelivered:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var ResultText=when(resultCode){
                Activity.RESULT_OK->" The sms is delivered"
                else->"The SMS is fail"
            }
            Show(ResultText)
        }
        abstract fun Show(message:String)
    }

    }


