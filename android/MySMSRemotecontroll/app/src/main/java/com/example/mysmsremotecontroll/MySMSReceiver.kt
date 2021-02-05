package com.example.mysmsremotecontroll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log

class MySMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
      var message= Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
      Log.v("SMS XXXX",message.displayMessageBody)
      var i=Intent(context,MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        if(message.displayMessageBody=="meaw"){
            i.putExtra("mode","meaw")
            context.startActivity(i)
            context.startActivity(i)
        }

        if(message.displayMessageBody=="alarm"){
            i.putExtra("mode","alarm")
            context.startActivity(i)
            context.startActivity(i)
        }

    }
}