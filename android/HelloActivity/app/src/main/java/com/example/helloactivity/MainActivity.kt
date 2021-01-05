package com.example.helloactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("This is ","On Create")
    }

    override fun onStart() {
        super.onStart()
        Log.v("This is ","On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.v("This is ","On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("This is ","On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("This is ","On Stop")
    }
}