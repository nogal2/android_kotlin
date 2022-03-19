package com.example.sample30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var userList = arrayListOf<DataVo>( // 나중엔 외부 데이터를 통해 불러오면된다(DB)
        DataVo("김철수", "kcs","서울시", 3000000, "kim"),
        DataVo("박상현", "phs", "부산시", 5000000, "park"),
        DataVo("최진형", "cjh", "광주시", 40000000, "choi"),
        DataVo("정수동", "jsd", "충주시", 4500000, "jung")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recycleView = findViewById<RecyclerView>(R.id.recycler_view)

        val mAdapter = CustomAdapter(this, userList)
        recycleView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        recycleView.layoutManager = layout  // activity_main에 RecyclerView에 LinearLayout이 들어감(기본값 vertical)

        recycleView.setHasFixedSize(true)   // 동등한 뷰의 형태로 만들어줌.


    }
}