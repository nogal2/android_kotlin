package com.example.membership

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.membership.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.insertBtn.setOnClickListener{
            startActivity(Intent(this, InsertActivity::class.java))
        }

        binding.selectBtn.setOnClickListener {
            startActivity(Intent(this, SelectActivity::class.java))
        }

        binding.allPrintBtn.setOnClickListener {
            var db = DBhelper.getInstance(this, "member.db")
            var database = db.writableDatabase
            var mem = db.allPrint(database)
            var apIntent = Intent(this , allPrintActivity::class.java)
            apIntent.putExtra("mem", mem)
            startActivity(apIntent)
        }

    }
}