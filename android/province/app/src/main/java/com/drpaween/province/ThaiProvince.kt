package com.drpaween.province

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

class ThaiProvince : ContentProvider() {

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
       return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        if (selection=="province"){
            return MakeCursor(context)
        }else{
            var cursor2=MatrixCursor(arrayOf("string"))
            cursor2.addRow(arrayOf("hello world"))
            return cursor2
        }
    }
    fun MakeCursor(context: Context?):Cursor{
        var cursor=MatrixCursor(arrayOf("name"))
        var file=context!!.resources.openRawResource(R.raw.province)
        var lines=file.bufferedReader(Charsets.UTF_8).readLines()
        for(line in lines){ cursor.addRow(arrayOf(line)) }
        return cursor
    }



    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}