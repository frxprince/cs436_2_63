package com.drpaween.mygps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.OnNmeaMessageListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(),LocationListener,OnNmeaMessageListener {
  lateinit var text1:TextView;  lateinit var text2:TextView;  lateinit var gps:LocationManager
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   text1=findViewById(R.id.textView);text2=findViewById(R.id.textView2)
   AskForGPS(); gps=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        gps.addNmeaListener(this)
    }


    @SuppressLint("MissingPermission")
    override fun onResume() {  super.onResume();AskForGPS();gps.requestLocationUpdates(LocationManager.GPS_PROVIDER,
    1000,10f,this)
    }
    override fun onPause() {
        super.onPause()
     gps.removeUpdates(this)
    }
    override fun onLocationChanged(location: Location?) {
text1.text="Lat:${location!!.latitude},Long:${location!!.longitude},Alt:${location!!.altitude}\n${location!!.time}"
    }
    override fun onNmeaMessage(message: String?, timestamp: Long) {
text2.text= message+"\n"+text2.text
    }
    fun AskForGPS(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
  ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
      Manifest.permission.ACCESS_COARSE_LOCATION),123)
        }
    }



    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {   }

}