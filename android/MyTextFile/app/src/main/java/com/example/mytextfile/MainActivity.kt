package com.example.mytextfile

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var BtnLoadResource=findViewById<Button>(R.id.buttonLoadResource)
        var BtnLoadAssets=findViewById<Button>(R.id.buttonLoadAssets)
        var BtnWriteSD=findViewById<Button>(R.id.buttonWriteSD)
        var BtnReadSD=findViewById<Button>(R.id.buttonReadSD)
        var TextFilename=findViewById<EditText>(R.id.edittextFilename)
        var TextContent=findViewById<EditText>(R.id.edittextContent)

        BtnLoadResource.setOnClickListener {

            var file=resources.openRawResource(R.raw.genesis01)
            var lines=file.bufferedReader(Charsets.UTF_8).readLines()
            var text=""
            for (line in lines){
                text=text+line+"\n"
            }
            TextContent.setText(text)
        }


        BtnLoadAssets.setOnClickListener {

            var file=assets.open("textfile/pangram.txt")
            var lines=file.bufferedReader(Charsets.UTF_8).readLines()
            var text=""
            for (line in lines){
                text=text+line+"\n"
            }
            TextContent.setText(text)
        }

  if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
  {
      ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)
  }

  BtnWriteSD.setOnClickListener {
 var file= File(Environment.getExternalStorageDirectory().toString()+"/"+TextFilename.text.toString())
      var outputfile=FileOutputStream(file)
      outputfile.write(TextContent.text.toString().toByteArray())
      outputfile.flush()
      outputfile.close()

  }

  BtnReadSD.setOnClickListener {
      var file=File(Environment.getExternalStorageDirectory().toString()+"/"+TextFilename.text.toString())
      var lines=file.bufferedReader(Charsets.UTF_8).readLines()
      var text=""
      for (line in lines){
          text=text+line+"\n"
      }
      TextContent.setText(text)

  }


    }
}