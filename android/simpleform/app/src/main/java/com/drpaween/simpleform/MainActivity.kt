package com.drpaween.simpleform

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    var province= mutableListOf<String>()
    lateinit var text1:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var spinner=findViewById<Spinner>(R.id.spinner)
        text1=findViewById<TextView>(R.id.textView)
        var cursor=contentResolver.query(
                Uri.parse("content://com.drpaween.thaiprovince"),null,
                "province",null,null
        )
        if(cursor!=null)
        {
            while(cursor.moveToNext()){province.add(cursor.getString(0)) }
        }
   var adapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,province)
     spinner.adapter=adapter
        spinner.onItemSelectedListener=this
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       text1.text=province.get(position)+"ID= $position"
    }
}