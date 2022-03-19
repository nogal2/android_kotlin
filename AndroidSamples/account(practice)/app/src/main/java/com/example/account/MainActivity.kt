package com.example.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.account.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.insertBtn.setOnClickListener {
            startActivity(Intent(this, InsertActivity::class.java))
        }

        binding.selectBtn.setOnClickListener {
            startActivity(Intent(this, SelectActivity::class.java))
        }

        binding.selectsBtn.setOnClickListener {
            startActivity(Intent(this, SelectsActivity::class.java))
        }

    }
}