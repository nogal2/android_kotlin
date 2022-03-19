package com.example.sample14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSpinnerFruit()
        setupSpinnerHandler()

    }
//    val fruit = arrayOf("과일선택", "사과", "배", "바나나", "포도") // 값이 외부에서(웹서버) 들어왔다고 가정

    // spinner에 값을 설정하는 함수
    fun setupSpinnerFruit() {

        val fruit = resources.getStringArray(R.array.fruit) // 내부에서 있다고 가정
        //어댑터 배열과 xml을 적용
        val adapter = ArrayAdapter(this, R.layout.item_spinner, fruit)
        // R.layout.item_spinner 안에 이곳에(this) 있는 배열 fruit을 붙여라.

        // spinner에 적용
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = adapter

    }

    // 선택시 결과를 출력해 주는 함수
    fun setupSpinnerHandler() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val textView = findViewById<TextView>(R.id.textView)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {    // item이 선택되어졌을 때
                textView.text = "선택됨: $position ${spinner.getItemAtPosition(position)}"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

}