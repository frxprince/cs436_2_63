package com.example.myvideo

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var videoView=findViewById<VideoView>(R.id.videoView)
        var BtnResource=findViewById<Button>(R.id.buttonResource)
        var BtnSDcard=findViewById<Button>(R.id.buttonSDcard)
        var BtnInternet=findViewById<Button>(R.id.buttonInternet)
        var BtnPlay=findViewById<Button>(R.id.buttonPlay)
        var BtnPause=findViewById<Button>(R.id.buttonPause)
        var BtnStop=findViewById<Button>(R.id.buttonStop)


    videoView.setMediaController(MediaController(this))

        BtnResource.setOnClickListener {  videoView.setVideoURI(Uri.parse("android.resource://"+packageName+"/" + R.raw.video )) }

        BtnPlay.setOnClickListener { videoView.start() }
        BtnPause.setOnClickListener { videoView.pause() }
        BtnStop.setOnClickListener { videoView.stopPlayback() }

  if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
  {
      ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1234)

  }
     BtnSDcard.setOnClickListener {videoView.setVideoURI( Uri.parse(Environment.getExternalStorageDirectory().path+"/videos/Loituma.3gp") ) }


        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),1234)

        }
     BtnInternet.setOnClickListener { videoView.setVideoURI( Uri.parse("http://www.drpaween.com/ohm/cs436/mv.mp4") ) }


    }
}