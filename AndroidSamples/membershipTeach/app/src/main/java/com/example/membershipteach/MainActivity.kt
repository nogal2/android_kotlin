package com.example.membershipteach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertBtn = findViewById<Button>(R.id.insertBtn)

        insertBtn.setOnClickListener {
            val i = Intent(this, InsertActivity::class.java)
            startActivity(i)
        }

    }
}