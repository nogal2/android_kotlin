package com.example.sample34binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample34binding.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.read.setOnClickListener{
            var student = intent.getParcelableExtra<Student>("student")
            if(student != null) {
                binding.textView.text = "${student.name} ${student.count} ${student.level}"
            }
        }

    }
}