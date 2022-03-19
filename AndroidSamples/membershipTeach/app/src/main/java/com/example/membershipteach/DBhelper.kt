package com.example.membershipteach

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//Dao

class DBhelper(context:Context, filename:String):SQLiteOpenHelper(context, filename, null, 1) {

    // 이걸 singleton 으로 만들어 놓는것이 편하다.
    companion object {
        private var dbhelper:DBhelper? = null
        fun getInstance(context: Context, filename: String): DBhelper {
            if(dbhelper == null) {
                dbhelper = DBhelper(context,filename)
            }
            return dbhelper!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql:String = "CREATE TABLE if not exists MEMBER( " +
                           "seq integer primary key autoincrement, " +
                           "name string, " +
                           "age integer, " +
                           "address string)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insert(vo:Member) {
        var sql = " INSERT INTO MEMBER(name, age, address) " +
                  " VALUES('${vo.name}', '${vo.age}', '${vo.address}')"

        var db = this.writableDatabase
        db.execSQL(sql)
    }

}