package com.example.sample08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar?.setDisplayShowHomeEnabled(false)    action bar 를 가려줌
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)    // 사용자 지정 메뉴를 추가해주라는 명령어.

        return super.onCreateOptionsMenu(menu)
        //return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {   // 클릭된 부분이 반응함
        println("onOptionsItemSelected~~")
        val textView:TextView = findViewById<TextView>(R.id.textView)
        when(item?.itemId) {
            R.id.menu_search -> textView.text = "검색 클릭"

            R.id.menu_chat -> textView.text = "채팅 클릭"

            R.id.menu_email -> textView.text = "이메일 클릭"

            R.id.action_setting -> textView.text = "세팅 클릭"
        }

        return super.onOptionsItemSelected(item)
    }
}