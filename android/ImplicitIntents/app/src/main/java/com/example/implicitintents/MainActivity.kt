package com.example.implicitintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.ContactsContract
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var BtnWeb=findViewById<Button>(R.id.button)
        var BtnMap=findViewById<Button>(R.id.button2)
        var BtnContact=findViewById<Button>(R.id.button3)
        var BtnAlarm=findViewById<Button>(R.id.button4)


        BtnWeb.setOnClickListener {
            var intent= Intent(Intent.ACTION_VIEW, Uri.parse("http://csmju.jowave.com"))
        if(intent.resolveActivity(packageManager)!=null)
        {
            startActivity(intent)
        }else{
            BtnWeb.text="No browser"
            BtnWeb.isEnabled=false
        }
        }

        BtnMap.setOnClickListener {
            var intent=Intent(Intent.ACTION_VIEW,Uri.parse("geo:18.8158634,99.2278802"))
            if(intent.resolveActivity(packageManager)!=null)
            {
                startActivity(intent)
            }else{
                BtnMap.text="No Map"
                BtnMap.isEnabled=false
            }

        }

        BtnContact.setOnClickListener {
            var intent=Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
            if(intent.resolveActivity(packageManager)!=null)
            {
                startActivity(intent)
            }else{
                BtnContact.text="No Phonebook"
                BtnContact.isEnabled=false
            }
        }

if (ContextCompat.checkSelfPermission(this,"com.android.alarm.permission.SET_ALARM")!=PackageManager.PERMISSION_GRANTED){
   ActivityCompat.requestPermissions(this, arrayOf("com.android.alarm.permission.SET_ALARM"),1234)
}
    BtnAlarm.setOnClickListener {
        var intent=Intent(AlarmClock.ACTION_SET_TIMER)
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,"Time's up")
        intent.putExtra(AlarmClock.EXTRA_LENGTH,10)
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI,true)
        if(intent.resolveActivity(packageManager)!=null)
        {
            startActivity(intent)
        }else{
            BtnAlarm.text="No Alarmclock"
            BtnAlarm.isEnabled=false
        }

    }

    }
}