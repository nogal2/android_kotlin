package com.example.sample35binding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample35binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.move.setOnClickListener{
            Singleton.chicken = binding.editText.text.toString()

            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

    }
}