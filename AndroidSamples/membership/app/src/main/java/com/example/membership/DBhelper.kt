package com.example.membership

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context:Context?, name:String?):
    SQLiteOpenHelper(context,name,null,1) {

    companion object {
        private var dbhelper:DBhelper? = null
        fun getInstance(context: Context, filename:String): DBhelper {
            if(dbhelper == null) {
                dbhelper = DBhelper(context,filename)
            }
            return dbhelper!!
        }
    }
    var mem:Member? = null
    private var memList = ArrayList<Member>()

    override fun onCreate(db: SQLiteDatabase?) {
        var sql:String = "CREATE TABLE IF NOT EXISTS MEMBER( " +
                         "SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "NAME TEXT, " +
                         "AGE INTEGER, " +
                         "RELATIONSHIP TEXT, " +
                         "JOB TEXT, " +
                         "ADDRESS TEXT, " +
                         "PHONE TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var sql:String = "DROP TABLE IF EXISTS MEMBER"
        db?.execSQL(sql)
        onCreate(db)
    }

    fun insert(vo:Member) {
        var sql = "INSERT INTO MEMBER(NAME, AGE, RELATIONSHIP, JOB, ADDRESS, PHONE)" +
                 " VALUES('${vo.name}','${vo.age}', '${vo.relationship}', '${vo.job}', '${vo.address}', '${vo.phone}')"
        var db = this.writableDatabase
        db.execSQL(sql)
    }

    fun delete(db:SQLiteDatabase, vo:Member) {
        var sql = "DELETE FROM MEMBER " +
                  "WHERE NAME = '${vo.name}'"
        db.execSQL(sql)
    }

    fun select(db:SQLiteDatabase, name:String):String {
        var sql = "SELECT * FROM MEMBER " +
                  "WHERE NAME = '${name}'"
        var result = db.rawQuery(sql,null)
        var str:String =""
        while(result.moveToNext()) {
            str += result.getString(result.getColumnIndex("SEQ")) + "\n" +
            "" + result.getString(result.getColumnIndex("NAME")) + "\n" +
            "" + result.getString(result.getColumnIndex("AGE")) + "\n" +
            "" + result.getString(result.getColumnIndex("RELATIONSHIP")) + "\n" +
            "" + result.getString(result.getColumnIndex("JOB")) + "\n" +
            "" + result.getString(result.getColumnIndex("ADDRESS")) + "\n" +
            "" + result.getString(result.getColumnIndex("PHONE"))

        }
        return str
    }

    @SuppressLint("Range")
    fun allPrint(db:SQLiteDatabase): ArrayList<Member> {
        var sql = "SELECT * FROM MEMBER"
        var result = db.rawQuery(sql, null)

        while(result.moveToNext()) {
            memList.add(
                Member(result.getInt(result.getColumnIndex("SEQ")),
                         result.getString(result.getColumnIndex("NAME")),
                         result.getInt(result.getColumnIndex("AGE")),
                         result.getString(result.getColumnIndex("RELATIONSHIP")),
                         result.getString(result.getColumnIndex("JOB")),
                         result.getString(result.getColumnIndex("ADDRESS")),
                         result.getString(result.getColumnIndex("PHONE")))
            )
            /*str += result.getString(result.getColumnIndex("SEQ")) + "\n" +
                    "" + result.getString(result.getColumnIndex("NAME")) + "\n" +
                    "" + result.getString(result.getColumnIndex("AGE")) + "\n" +
                    "" + result.getString(result.getColumnIndex("ADDRESS"))*/
        }
        return memList
    }



}