package com.example.mybatterymonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var receiver:MyRecriver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text1=findViewById<TextView>(R.id.textView)
        var progress1=findViewById<ProgressBar>(R.id.progressBar)
        progress1.max=100

         receiver=object: MyRecriver(){
            override fun update(batt_value: Float, Usb: Int) {
                progress1.progress=batt_value.toInt()
                var USBstatus= when(Usb){
                    BatteryManager.BATTERY_STATUS_CHARGING->"Charging"
                    BatteryManager.BATTERY_STATUS_DISCHARGING->"Discharging"
                    BatteryManager.BATTERY_STATUS_FULL->"Full power"
                    BatteryManager.BATTERY_STATUS_NOT_CHARGING->"Not charging"
                    else->"I don't know"
                }
                text1.text=USBstatus
            }
        }


    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }


    abstract class MyRecriver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var level=intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)?:0
            var scale=intent?.getIntExtra(BatteryManager.EXTRA_SCALE,1)?:1
            var charging=intent?.getIntExtra(BatteryManager.EXTRA_STATUS,0)?:0
            var batt_value=(level/scale.toFloat())*100
            update(batt_value,charging)
        }
        abstract fun update(batt_value:Float,Usb:Int)


    }
}