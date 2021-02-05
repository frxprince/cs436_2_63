package com.example.mysmsremotecontroll

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if(intent?.getStringExtra("mode")?:"no data" =="meaw") {
            var Mp = MediaPlayer.create(this, R.raw.cat)
            Mp.start()
        }

        if(intent?.getStringExtra("mode")?:"no data" =="alarm") {
            var Mp = MediaPlayer.create(this, R.raw.alarm)
            Mp.start()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED)
    {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS),1234)

    }

    }
}