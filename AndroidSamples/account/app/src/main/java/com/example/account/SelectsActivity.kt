package com.example.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.account.databinding.ActivitySelectsBinding

class SelectsActivity : AppCompatActivity() {

    val binding by lazy { ActivitySelectsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSelcects.setOnClickListener {
            var db = DBhelper.getInstance(this, "account.db")
            val startDate = String.format("%d-%02d-%02d", binding.startDate.year,binding.startDate.month+1,binding.startDate.dayOfMonth)
            val endDate = String.format("%d-%02d-%02d", binding.endDate.year,binding.endDate.month+1,binding.endDate.dayOfMonth)
            var list = db.selects(startDate,endDate)

            val mAdapter = CustomAdapter(this, list)
            binding.recyclerView.adapter = mAdapter

            val layout = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = layout

            binding.recyclerView.setHasFixedSize(true)
        }




    }
}