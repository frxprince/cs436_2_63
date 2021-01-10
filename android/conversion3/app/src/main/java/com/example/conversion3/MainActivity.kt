package com.example.conversion3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        setContentView(R.layout.activity_main)
        var welcomeText=findViewById<TextView>(R.id.textwelcome)

       welcomeText.setOnClickListener {
          var activity2Intent= Intent(this,Activity2::class.java)
          startActivity(activity2Intent)
       }
    }
}