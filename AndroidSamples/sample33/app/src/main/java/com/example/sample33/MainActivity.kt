package com.example.sample33

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val write = findViewById<Button>(R.id.write)
        val move = findViewById<Button>(R.id.move)

        val edit = findViewById<EditText>(R.id.editText)

        write.setOnClickListener {
            val pref = getSharedPreferences("pref", MODE_PRIVATE)

            val editor = pref.edit()    //초기화 pref에 대한 공간을 가져온 것 (여행 가방을 연 상태)
            editor.putString("mydata", edit.text.toString())    // 짐을 넣은것.
            editor.commit()     //저장

            edit.setText("")    //초기화
        }
        move.setOnClickListener{
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }
    }
}