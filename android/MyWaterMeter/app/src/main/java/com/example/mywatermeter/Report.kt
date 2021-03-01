package com.example.mywatermeter

import android.Manifest
import android.app.ListActivity
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream

class Report : ListActivity() {


  var ID:String=""
    lateinit var DB:SQLiteDatabase
lateinit var textMeterID: EditText
lateinit var textMeterValue:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)


        textMeterID=findViewById(R.id.editTextViewMeterID)
        textMeterValue=findViewById(R.id.editTextViewMeterValue)

        var conn=MyDatabase(this,"water.sqlite",null,1)
        DB=conn.writableDatabase
        paintgrid()

        var BtnUpdate=findViewById<Button>(R.id.buttonUpdate)
        var Btndelete=findViewById<Button>(R.id.buttonDelete)
        var BtnExport=findViewById<Button>(R.id.buttonExcel)


        BtnUpdate.setOnClickListener {
          DB.execSQL("update waterdata set meter_id=${textMeterID.text.toString()},meter_value=${textMeterValue.text.toString()} " +
                  "where id=$ID")
            paintgrid()
        }

        Btndelete.setOnClickListener {
           DB.execSQL("delete from waterdata where id=$ID")
            paintgrid()
        }


        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)

        }

       BtnExport.setOnClickListener {
      var cursor=DB.rawQuery("select id,meter_id,meter_value,timestamp from waterdata order by meter_id",null)
      var file= File(Environment.getExternalStorageDirectory().toString()+"/myDBexport.csv")
      var outputfile=FileOutputStream(file)
      outputfile.write("ID,Meter_id,Meter_value,timestasmp\n".toByteArray())
        while (cursor.moveToNext()){
       outputfile.write(
           "${cursor.getString(0)},${cursor.getString(1)},${cursor.getString(2)},${cursor.getString(3)}\n".toByteArray())
        outputfile.flush()

        }
           outputfile.close()
        Toast.makeText(this,"The file is saved!",Toast.LENGTH_LONG).show()
       }




    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        var cursor=listAdapter.getItem(position) as Cursor
        textMeterID.setText(cursor.getString(1))
        textMeterValue.setText(cursor.getString(2))
        ID=cursor.getString(0)


    }

    fun paintgrid(){
  var cursor=DB.rawQuery("select id as _id,meter_id,meter_value,timestamp from waterdata",null)
  var columns= arrayOf("meter_id","meter_value","timestamp")
  var map_to= intArrayOf(R.id.textMeterID,R.id.textMetervalue,R.id.textdatetime)
        var myadapter=SimpleCursorAdapter(this,R.layout.listview_item,cursor,columns,map_to,0)
     listAdapter = myadapter


    }
}