package com.example.sample35binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample35binding.databinding.ActivityMainBinding
import com.example.sample35binding.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val binding by lazy{ ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text = Singleton.chicken

    }
}