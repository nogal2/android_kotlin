package com.example.work

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.work.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(binding.root)

        var counter = 0
        binding.btnPlus.setOnClickListener {
            counter++
            binding.textView.text = counter.toString()
        }
        binding.btnMinus.setOnClickListener {
            counter--
            binding.textView.text = counter.toString()
        }

        binding.btnReset.setOnClickListener {
            counter = 0
            binding.textView.text = counter.toString()
        }

        binding.btnSet.setOnClickListener {
            binding.textView.text = binding.inputNum.text
            counter = binding.textView.text.toString().toInt()
        }

        if(binding.textView.text == "0" || binding.textView.text == 0.toString()) {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("counter")
                .setMessage("+를 누르면 1씩 증가, -를 누르면 1씩 감소, reset을 누르면 0부터, set을 누르면 입력 값에서 시작")
                .setCancelable(false)
                .setNeutralButton("닫기", DialogInterface.OnClickListener{dialog, which->}).show()
        }

    }
}