package com.drpaween.getcontactlist

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    lateinit var Text1: TextView
    lateinit var Text2:TextView
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode==321) && (resultCode==Activity.RESULT_OK)){
    var P= arrayOf(ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER,
        ContactsContract.Contacts._ID)
     var uri=data?.data?: Uri.parse("")
     var cursor=contentResolver.query(uri,P,null,null,null)
     cursor!!.moveToFirst()
     Text1.text=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
     Text2.text= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
     if(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))=="1")
     {
     var cursor2=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
     ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+
             cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)),null,
         null)
      cursor2!!.moveToFirst()
         Text2.text=cursor2.getString(cursor2.getColumnIndex("data1"))
      cursor2.close()
     }
            cursor.close()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
  if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
      !=PackageManager.PERMISSION_GRANTED){
ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),123)
  }
    Text1=findViewById(R.id.textView)
    Text2=findViewById(R.id.textView2)
    var Btn=findViewById<Button>(R.id.button)
        Btn.setOnClickListener {
  var i= Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
            startActivityForResult(i,321)
        }
    }
}