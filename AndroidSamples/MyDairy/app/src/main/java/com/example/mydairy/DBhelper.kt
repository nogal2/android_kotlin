package com.example.mydairy

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context: Context?, name:String?):SQLiteOpenHelper(context,name,null,1) {
    companion object {
        private var dbHelper:DBhelper? = null
        fun getInstance(context:Context, filename:String): DBhelper {
            if(dbHelper == null) {
                dbHelper = DBhelper(context,filename)
            }
            return dbHelper!!
        }
    }

    private var list = ArrayList<DataVo>()

    override fun onCreate(db: SQLiteDatabase?) {
        var sql:String = "CREATE TABLE IF NOT EXISTS MYDAIRY( " +
                "SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "IMAGEPATH TEXT, " +
                "ADDRESS TEXT, " +
                "CONTENT TEXT, " +
                "REGIDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        var sql:String = "DROP TABLE IF EXISTS MYDOCUMENT"
        db?.execSQL(sql)
        onCreate(db)
    }

    fun insert(vo:DataVo) {
        var sql = "INSERT INTO MYDOCUMENT(IMAGEPATH, ADDRESS, CONTENT) " +
                "VALUES('${vo.imagePath}', '${vo.address}', '${vo.content}')"
        var db = writableDatabase
        db.execSQL(sql)
    }

    @SuppressLint("Range")
    fun viewSelect():ArrayList<DataVo> {
        var sql = "SELECT * FROM MYDOCUMENT"
        var db = writableDatabase
        var result = db.rawQuery(sql, null)
        while(result.moveToNext()) {
            val seq = result.getInt(result.getColumnIndex("SEQ"))
            val path = result.getString(result.getColumnIndex("IMAGEPATH"))
            val addr = result.getString(result.getColumnIndex("ADDRESS"))
            val content = result.getString(result.getColumnIndex("CONTENT"))
            val date = result.getString(result.getColumnIndex("REGIDATE"))

            list.add(DataVo(seq,path,addr,content,date))
        }
        return list
    }


}