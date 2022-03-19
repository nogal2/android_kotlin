package com.example.sample33binding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample33binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.write.setOnClickListener{
            val pref = getSharedPreferences("pref", MODE_PRIVATE)

            val editor = pref.edit()
            editor.putString("mydata", binding.editText.text.toString())
            editor.commit()

            binding.editText.setText("")
        }

        binding.move.setOnClickListener{
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

    }
}