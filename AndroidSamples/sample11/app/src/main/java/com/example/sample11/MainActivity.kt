package com.example.sample11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.TextView
import com.example.sample11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        setContentView(binding.root)
        // setContentView(R.layout.activity_main)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId->
            Log.d("RadioButton", "radio버튼 클릭")
            when(checkedId) {
                R.id.radio1 -> binding.textView.text = "사과가 선택되었습니다."
                R.id.radio2 -> binding.textView.text = "바나나가 선택되었습니다."
                R.id.radio3 -> binding.textView.text = "오렌지가 선택되었습니다."
            }
        }
*/
        setContentView(R.layout.activity_main)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val textView = findViewById<TextView>(R.id.textView)

        radioGroup.setOnCheckedChangeListener { _, checkedId->
            Log.d("RadioButton", "radio버튼 클릭")
            when(checkedId) {
                R.id.radio1 -> textView.text = "사과가 선택되었습니다."
                R.id.radio2 -> textView.text = "바나나가 선택되었습니다."
                R.id.radio3 -> textView.text = "오렌지가 선택되었습니다."
            }
        }
    }
}