package com.drpaween.province

import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text1=findViewById<TextView>(R.id.textView)
        text1.movementMethod=ScrollingMovementMethod()
        var btnquery=findViewById<Button>(R.id.buttonQuery)
        btnquery.setOnClickListener {
      var cursor=contentResolver.query(
          Uri.parse("content://com.drpaween.thaiprovince"),null,
          "province",null,null
                                     )
         text1.text=""
         if(cursor!=null){
             while(cursor.moveToNext()){
                text1.text=text1.text.toString()+cursor.getString(0)+"\n"
             }
         }
        }
    }
}