package com.example.sample17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.Dimension
import com.example.sample17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    var items = arrayOf(
        "서울", "부산", "대구", "광주", "대전", "울산", "전주", "목포",
        "제주", "강원", "태백", "인천", "서울", "부산", "대구", "광주",
        "대전", "울산", "전주", "목포", "제주", "강원", "태백", "인천",
        "제주", "강원", "태백", "인천", "서울", "부산", "대구", "광주",
        "대전", "울산", "전주", "목포", "제주", "강원", "태백", "인천"
    )
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
//        val listView = findViewById<View>(R.id.listView) as ListView    // 뭔지 모를때는 제네릭을 View 로 적고 as로 캐스트변환 하면 됨.
//        val textView = findViewById<TextView>(R.id.textView)
//
//        textView.setTextSize(Dimension.SP, 28.0f)
        binding.textView.setTextSize(Dimension.SP, 28.0f)

        // Adapter
        val adapter:ArrayAdapter<*> = ArrayAdapter<Any?>(this, R.layout.item_spinner, items)  // <*>어떤 형태든 받아들이겠다. <Any?> 어떤 형태든 그리고 null이라도 받아들이겠다.
        binding.listView.adapter = adapter
        binding.listView.onItemClickListener

        }
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
            val textView = findViewById<TextView>(R.id.textView)
            textView.text = items[pos]
        }
}



