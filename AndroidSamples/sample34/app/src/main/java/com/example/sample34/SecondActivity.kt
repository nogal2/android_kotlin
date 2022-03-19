package com.example.sample34

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val read = findViewById<Button>(R.id.read)
        val textView = findViewById<TextView>(R.id.textView)

        read.setOnClickListener{
            // 짐 풀기
            var student = intent.getParcelableExtra<Student>("student")
            if(student != null) {
                textView.text = "${student.name} ${student.count} ${student.level}"
            }

        }


    }
}