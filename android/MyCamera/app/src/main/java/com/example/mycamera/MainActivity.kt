package com.example.mycamera

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var imageView:ImageView
    var Image: Bitmap?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==4567 && resultCode== Activity.RESULT_OK ){
            if(Image!=null)Image?.recycle()
            Image=data?.extras?.getParcelable<Bitmap>("data")
            var canvas= Canvas(Image!!)
            var paint= Paint()
            paint.setColor(Color.MAGENTA)
            paint.textSize=8F
            paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_OVER))
            canvas.drawBitmap(Image!!,0F,0F,paint)
            canvas.drawText(Calendar.getInstance().time.toString(),1F,20F,paint)
      imageView.setImageBitmap(Image)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var BtnSave=findViewById<Button>(R.id.button)
        var TextFilename=findViewById<EditText>(R.id.edittextFilename)
        imageView=findViewById(R.id.imageView)

        imageView.setOnClickListener {
     var cameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent,4567)
        }

   if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
       ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)
   }
      BtnSave.setOnClickListener {
      if(Image!=null){
   var file= File(Environment.getExternalStorageDirectory().toString()+"/"+TextFilename.text.toString())
    file.createNewFile()
    var bos=ByteArrayOutputStream()
          Image?.compress(Bitmap.CompressFormat.JPEG,99,bos)
          var fos=FileOutputStream(file)
          fos.write(bos.toByteArray())
          fos.flush()
          fos.close()
      }

      }

    }

    override fun onResume() {
        super.onResume()
        if((Image?.width?:0)<10){{
            imageView.setImageBitmap((Image))
        }}



    }
}