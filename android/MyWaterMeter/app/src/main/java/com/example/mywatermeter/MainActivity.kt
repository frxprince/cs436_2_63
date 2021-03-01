package com.example.mywatermeter

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var conn=MyDatabase(this,"water.sqlite",null,1)
        var DB=conn.writableDatabase

        var BtnSave=findViewById<Button>(R.id.buttonSave)
        var BtnReport=findViewById<Button>(R.id.buttonReport)
        var TextMeterID=findViewById<EditText>(R.id.editTextMeterID)
        var TextMeterValue=findViewById<EditText>(R.id.editTextMeterValue)

        BtnSave.setOnClickListener {
         DB.execSQL("insert into waterdata(meter_id,meter_value)values(${TextMeterID.text.toString()},${TextMeterValue.text.toString()} )")
         TextMeterID.setText("")
            TextMeterValue.setText("")

        }

        BtnReport.setOnClickListener {
      var i= Intent(this,Report::class.java)
            startActivity(i)


/*       var  cursor=DB.rawQuery("select id,meter_id,meter_value,timestamp from waterdata",null)
          while (cursor.moveToNext()){
     Log.v("test database"," ${cursor.getString(0)} ,${cursor.getString(1)}, ${cursor.getString(2)} ," +
             "${cursor.getString(3)}     ")

          }
*/

        }



    }
}