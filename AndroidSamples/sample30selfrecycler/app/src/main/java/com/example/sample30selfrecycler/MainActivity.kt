package com.example.sample30selfrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var userList = arrayListOf<DataVo>(
        DataVo("김철수", "kcs", "서울시", 30, "kim"),
        DataVo("박수진", "psj", "부산시", 355, "park"),
        DataVo("이지형", "ljh", "광주시", 500, "lee"),
        DataVo("정승익", "jsi", "충주시", 600, "jung")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)



    }
}