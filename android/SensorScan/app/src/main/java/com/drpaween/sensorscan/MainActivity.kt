package com.drpaween.sensorscan

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import org.w3c.dom.Text
import java.util.logging.ErrorManager

class MainActivity : AppCompatActivity(),SensorEventListener,AdapterView.OnItemSelectedListener {
    lateinit var Text1:TextView
    lateinit var Text2:TextView
    lateinit var manager: SensorManager
    lateinit var sensor:Sensor
    lateinit var allsensor:MutableList<Sensor>
    override fun onPause() {
        super.onPause()
     manager.unregisterListener(this,sensor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Text1 =findViewById(R.id.textView)
        Text2=findViewById(R.id.textView2)
        var spinner=findViewById<Spinner>(R.id.spinner)
        manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        allsensor=manager.getSensorList(Sensor.TYPE_ALL)
        var sensor_name= mutableListOf<String>()
        for(name in allsensor){sensor_name.add(name.name)}
     var adapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,sensor_name)
       spinner.adapter=adapter;spinner.onItemSelectedListener=this;sensor=allsensor[0]
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    if(sensor!=null)manager.unregisterListener(this,sensor)
    sensor=allsensor[position]
    manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
Text1.text="Name:${sensor.name}\nVender${sensor.vendor}\nVersion:${sensor.version}\n" +
        "Max:${sensor.maximumRange}   Resolution:${sensor.resolution}"
    }
    override fun onSensorChanged(event: SensorEvent?) {
var msg:String=""
        for((index,item) in event!!.values.withIndex()){msg+="Parameter[$index] = $item\n"}
     Text2.text=msg
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {    }
    override fun onNothingSelected(parent: AdapterView<*>?) {    }
}