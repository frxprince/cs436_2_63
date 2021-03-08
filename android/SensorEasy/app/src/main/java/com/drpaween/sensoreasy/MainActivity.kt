package com.drpaween.sensoreasy

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(),SensorEventListener {
    lateinit var manager:SensorManager
    lateinit var sensor1:Sensor
    lateinit var sensor2:Sensor
    lateinit var Text1:TextView
    lateinit var Text2:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     var BtnStart=findViewById<Button>(R.id.buttonStart)
     var BtnStop=findViewById<Button>(R.id.buttonStop)
     Text1=findViewById(R.id.textView)
     Text2=findViewById(R.id.textView2)
    manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
    sensor1=manager.getDefaultSensor(Sensor.TYPE_LIGHT)
    sensor2=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
     BtnStart.setOnClickListener {
         manager.registerListener(this,sensor1,SensorManager.SENSOR_DELAY_NORMAL)
         manager.registerListener(this,sensor2,SensorManager.SENSOR_DELAY_NORMAL)
     }
    BtnStop.setOnClickListener {
        manager.unregisterListener(this,sensor1)
        manager.unregisterListener(this,sensor2)
    }
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor==sensor1)Text1.text=event.values[0].toString()
        if(event!!.sensor==sensor2)Text2.text="${event.values[0]},${event.values[1]},${event.values[2]}"
    }




    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
       // TODO("Not yet implemented")
    }


}