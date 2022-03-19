package com.example.account

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.account.vo.Account

class DBhelper(context: Context?, name:String?):SQLiteOpenHelper(context,name,null,1) {

    companion object {
        private var dbHelper:DBhelper? = null
        fun getInstance(context: Context, filename:String): DBhelper {
            if(dbHelper == null) {
                dbHelper = DBhelper(context,filename)
            }
            return dbHelper!!
        }
    }
    private var list = ArrayList<Account>()
    override fun onCreate(db: SQLiteDatabase?) {
        var sql:String = "CREATE TABLE IF NOT EXISTS ACCOUNT( " +
                         "SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "ICOG STRING," +
                         "TITLE STRING, " +
                         "DATE DATE," +
                         "AMOUNT INTEGER," +
                         "MEMO STRING)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insert(vo:Account) {
        var sql = "INSERT INTO ACCOUNT(ICOG, TITLE, DATE, AMOUNT, MEMO) " +
                  "VALUES('${vo.icOg}','${vo.title}','${vo.date}','${vo.Amount}','${vo.memo}')"
        var db = writableDatabase
        db.execSQL(sql)
    }


    @SuppressLint("Range")
    fun select(date:String): ArrayList<Account> {
        var sql = "SELECT * FROM ACCOUNT " +
                  "WHERE DATE = '${date}'"
        var db = writableDatabase
        var result = db.rawQuery(sql,null)
        while(result.moveToNext()) {
            val seq = result.getInt(result.getColumnIndex("SEQ"))
            val icOg = result.getString(result.getColumnIndex("ICOG"))
            val title = result.getString(result.getColumnIndex("TITLE"))
            val date = result.getString(result.getColumnIndex("DATE"))
            val amount = result.getInt(result.getColumnIndex("AMOUNT"))
            val memo = result.getString(result.getColumnIndex("MEMO"))

            list.add(Account(seq,icOg,title,date,amount,memo))
        }
        return list
    }

    @SuppressLint("Range")
    fun selects(dateOne:String, dateTwo:String):ArrayList<Account> {
        var sql = "SELECT * FROM ACCOUNT " +
                  "WHERE DATE BETWEEN '$dateOne' AND '$dateTwo' " +
                  "ORDER BY DATE"
        Log.d("날짜", "$dateOne, $dateTwo")

        var db = writableDatabase
        var result = db.rawQuery(sql, null)
        while(result.moveToNext()) {
            val seq = result.getInt(result.getColumnIndex("SEQ"))
            val icOg = result.getString(result.getColumnIndex("ICOG"))
            val title = result.getString(result.getColumnIndex("TITLE"))
            val date = result.getString(result.getColumnIndex("DATE"))
            val amount = result.getInt(result.getColumnIndex("AMOUNT"))
            val memo = result.getString(result.getColumnIndex("MEMO"))
            list.add(Account(seq,icOg,title,date,amount,memo))
            Log.d("리스트", "$list")
        }
        Log.d("리스트목록", "${list[0].title}, ${list[1].title},${list[2].title},${list[3].title},${list[4].title}")
        return list
    }

}