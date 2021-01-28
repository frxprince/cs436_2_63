package com.example.myaudioplayer

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    lateinit var Mp:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var BtnLoadResource=findViewById<Button>(R.id.buttonLoadResource)
        var BtnPlay=findViewById<Button>(R.id.buttonPlay)
        var BtnPause=findViewById<Button>(R.id.buttonPause)
        var BtnStop=findViewById<Button>(R.id.buttonStop)
        var BtnLoadResource2=findViewById<Button>(R.id.buttonLoadResource2)
        var BtnLoadAssets=findViewById<Button>(R.id.buttonLoadAsset)
        var BtnInternet=findViewById<Button>(R.id.buttonInternet)
        var BtnSDCARD=findViewById<Button>(R.id.buttonLoadSD)


        BtnLoadResource.setOnClickListener {
            Mp= MediaPlayer()
            Mp= MediaPlayer.create(this,R.raw.cat)
        }

        BtnPlay.setOnClickListener {
            Mp.start()
        }
        BtnPause.setOnClickListener {
            Mp.pause()
        }
        BtnStop.setOnClickListener {
            Mp.stop()
        }

        BtnLoadResource2.setOnClickListener {
            Mp=MediaPlayer.create(this,R.raw.greeting)
        }
        BtnLoadAssets.setOnClickListener {
            Mp= MediaPlayer()
            var mp3file=assets.openFd("mp3/01/tada.mp3")
            Mp.setDataSource(mp3file.fileDescriptor,mp3file.startOffset,mp3file.length)
            Mp.prepare()
        }
if(ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),1234)
}

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1234)
        }
        BtnInternet.setOnClickListener {
            Mp= MediaPlayer()

            try {
                Mp.setDataSource(this, Uri.parse("http://www.drpaween.com/ohm/mp3test.mp3"))
                Mp.prepare()
            } catch (e: Exception) {
                Mp= MediaPlayer.create(this,R.raw.cat)
            }
        }

    BtnSDCARD.setOnClickListener {
        Mp= MediaPlayer()
        Mp.setDataSource(Environment.getExternalStorageDirectory().path+"/mp3/song.mp3")
        Mp.prepare()
    }

    }
}