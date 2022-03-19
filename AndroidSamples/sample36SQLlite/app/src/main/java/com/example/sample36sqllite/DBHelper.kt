package com.example.sample36sqllite

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(context:Context?, name:String?, factory: SQLiteDatabase.CursorFactory?,version:Int)
    : SQLiteOpenHelper(context,name,factory,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        //쿼리문
        var sql:String = "CREATE TABLE IF NOT EXISTS MYTABLE(" +
                         "  SEQ INTEGER PRIMARY KEY AUTOINCREMENT, "+ // 첫번째 이렇게 넣으면 시퀀스
                         "  TXT TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //쿼리문
        var sql:String = " DROP TABLE IF EXISTS MYTABLE "
        db?.execSQL(sql)
        onCreate(db)
    }

    fun insert(db:SQLiteDatabase, txt:String) {

        var sql = "INSERT INTO MYTABLE(TXT)" +
                "  VALUES('${txt}')"

        db.execSQL(sql)
    }

    fun delete(db:SQLiteDatabase, txt:String) {
        var sql = "DELETE FROM MYTABLE " +
                  "WHERE TXT = '${txt}'"
        db.execSQL(sql)
    }

    @SuppressLint("Range")
    fun select(db: SQLiteDatabase, txt:String) : String{
        var sql = " SELECT * FROM MYTABLE " +
                "   WHERE TXT='${txt}' "
        var result = db.rawQuery(sql, null)

        var str:String = ""
        while (result.moveToNext()){
            /*str += result.getString(result.getColumnIndex("SEQ")) + " " +
                    "" + result.getString(result.getColumnIndex("TXT"))*/
            str = result.getString(result.getColumnIndex("TXT"))
        }

        return str
    }
    /*fun select(db:SQLiteDatabase, txt:String): String?{
        var sql = "SELECT TXT" +
                  "FROM MYTABLE " +
                  "WHERE TXT = '${txt}'"
        var str:String? = ""
        var result = db.rawQuery(sql,null)
        while(result.moveToNext()) {
            str += result.getString(result.getColumnIndex("SEQ")) + " " +
                    "" + result.getString(result.getColumnIndex("TXT"))
        }


        return str

    }*/

    fun update(db:SQLiteDatabase, res:String, sel:String) {
        val sql = "UPDATE MYTABLE " +
                  "SET TXT ='${res}'" +
                  "WHERE TXT = '${sel}'"
        db.execSQL(sql)
    }

}