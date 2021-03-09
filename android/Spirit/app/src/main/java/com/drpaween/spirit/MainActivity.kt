package com.drpaween.spirit

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.math.PI
import kotlin.math.atan2

class MainActivity : AppCompatActivity(),SensorEventListener {
    lateinit var seekBar:SeekBar
    lateinit var Text1:TextView
    lateinit var Text2:TextView
    lateinit var manager:SensorManager
    lateinit var sensor:Sensor
    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this,sensor)
    }
    override fun onResume() {
        super.onResume()
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    seekBar=findViewById(R.id.seekBar)
    Text1=findViewById(R.id.textView)
    Text2=findViewById(R.id.textView2)
    manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
    sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    seekBar.max=180
     seekBar.progressDrawable=ContextCompat.getDrawable(this,R.mipmap.bg)
    seekBar.background=ContextCompat.getDrawable(this,R.mipmap.border)
    }
    override fun onSensorChanged(event: SensorEvent?) {
     Text1.text="X: ${event!!.values[0]}, Y:${event!!.values[1]}, Z:${event!!.values[2]} "
     var degree=180-((atan2(event!!.values[0],event!!.values[1])*180)/PI)-90.0
Text2.text=degree.toString()
        seekBar.progress=(90-degree).toInt()
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}