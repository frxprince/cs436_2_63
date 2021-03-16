package com.drpaween.myexcnahge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
 lateinit var spinner:Spinner
 lateinit var textinput:EditText
 lateinit var text:TextView
 lateinit var rate_map:JSONObject
 var currency_list= mutableListOf<String>()
 var THB_to_EURO:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner=findViewById(R.id.spinner)
        textinput=findViewById(R.id.editTextNumberDecimal)
        text=findViewById(R.id.textView)
            fun HttpGetDone(){
                this@MainActivity.runOnUiThread(
                    Runnable { var adapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,currency_list)
                    spinner.adapter=adapter; spinner.onItemSelectedListener=this
                    }
                )
            }
       doAsync { var conn= URL("https://api.exchangeratesapi.io/latest").openConnection() as HttpURLConnection;
           conn.requestMethod="GET";conn.connect();var response=BufferedReader(InputStreamReader(conn.inputStream)).readLine()
       var exchange_rate=JSONObject(response);var curency=exchange_rate.getString("rates")
           rate_map=JSONArray("["+curency+"]").getJSONObject(0)
     for(i in rate_map.keys()){
         if(i=="THB")THB_to_EURO=rate_map.getDouble(i)
         currency_list.add((i))
     }
           HttpGetDone()
       }
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    var result=(textinput.text.toString().toDouble()/THB_to_EURO) *rate_map.getDouble(currency_list[position])
    text.text=result.toString()+" "+ currency_list[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}


}