package com.example.membersprrestful

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.membersprrestful.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 리스트를 생성후 실제 리사이클뷰가 작동되는지 확인하고 DB를 불러오는게 좋다.
    val list = arrayListOf<MemberDto>(
        MemberDto("kim", "kkk", "김", "kim@naver.com", 3),
        MemberDto("park", "ppp", "박", "park@naver.com", 3),
        MemberDto("lee", "lll", "이", "lee@naver.com", 3)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val memList = MemberDao.getInstance().allMember()
        println(memList[0].name)

        val mAdapter = CustomAdapter(this, memList)
        binding.recyclerView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layout
        binding.recyclerView.setHasFixedSize(true)

    }
}