package com.example.sample33binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample33binding.databinding.ActivityMainBinding
import com.example.sample33binding.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.read.setOnClickListener{
            val pref = getSharedPreferences("pref", MODE_PRIVATE)

            binding.textView.text = pref.getString("mydata", "")
        }

    }
}