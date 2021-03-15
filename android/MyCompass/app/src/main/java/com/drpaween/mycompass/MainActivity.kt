package com.drpaween.mycompass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import kotlin.math.PI
import kotlin.math.atan2

class MainActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener,SensorEventListener {
    lateinit var seekBar: SeekBar
    lateinit var text1:TextView
    lateinit var imageview:ImageView
    lateinit var manager:SensorManager
    lateinit var sensor:Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     seekBar=findViewById(R.id.seekBar)
     text1=findViewById(R.id.textView)
     imageview=findViewById(R.id.imageView)
     manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
     sensor=manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
     seekBar.max=360;seekBar.progress=180;seekBar.setOnSeekBarChangeListener(this)
    }

    override fun onResume() {
        super.onResume()
      manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause() {
        super.onPause()
      manager.unregisterListener(this,sensor)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        var x=event!!.values[0];var y=event!!.values[1]
        var degree=((-atan2(y,x)/PI)*180)
        text1.text="$degree\nx:$x\ny:$y"
        imageview.rotation=degree.toFloat()-180
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
       imageview.rotation=seekBar!!.progress .toFloat()-180
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


}