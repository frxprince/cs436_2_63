package com.example.mylauncher

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var displayList= mutableListOf<String>()
        var package_list=packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for(item in package_list){
            displayList.add(item.packageName)
        }
        var listview=findViewById<ListView>(R.id.listview1)
        var t1=findViewById<TextView>(R.id.textView)
        var t2=findViewById<TextView>(R.id.textView2)
        var Btn=findViewById<Button>(R.id.button)


        var adapter= ArrayAdapter(this,R.layout.activity_listitem,displayList.toTypedArray())
        listview.adapter=adapter

        listview.setOnItemClickListener { adapterView:AdapterView<*>, view1:View, i:Int, l:Long ->

            t1.text=listview.getItemAtPosition(i).toString()
            if(packageManager.getLaunchIntentForPackage(t1.text.toString())!=null)
            {
                t2.text=packageManager.getLaunchIntentForPackage(t1.text.toString()).toString()

            }else{
                t2.text="no launcher"
            }

        }

        Btn.setOnClickListener {
            startActivity(packageManager.getLaunchIntentForPackage(t1.text.toString()))
        }

    }
}


