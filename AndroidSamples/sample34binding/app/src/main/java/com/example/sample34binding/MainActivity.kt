package com.example.sample34binding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample34binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.move.setOnClickListener{
            var student = Student(binding.editName.text.toString(), binding.editCount.text.toString().toInt(), binding.editLevel.text.toString())

            var nextIntent = Intent(this, SecondActivity::class.java)

            nextIntent.putExtra("student", student)

            startActivity(nextIntent)
        }

    }
}