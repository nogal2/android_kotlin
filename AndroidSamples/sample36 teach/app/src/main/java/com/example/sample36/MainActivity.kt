package com.example.sample36

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbHelper = DBHelper(this, "mydb.db", null, 1)

        val insertBtn = findViewById<Button>(R.id.insertBtn)
        val editInsert = findViewById<EditText>(R.id.editInsert)

        var database = dbHelper.writableDatabase

        insertBtn.setOnClickListener {
            val txt = editInsert.text

            dbHelper.insert(database, txt.toString())
        }

        val selectBtn = findViewById<Button>(R.id.selectBtn)
        val editSelect = findViewById<EditText>(R.id.editSelect)
        val editSelectresult = findViewById<EditText>(R.id.editSelectresult)

        selectBtn.setOnClickListener {
            val txt = editSelect.text

            val resultTxt = dbHelper!!.select(database, txt.toString())
            editSelectresult.setText(resultTxt)
        }


    }
}