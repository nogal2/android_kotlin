package com.example.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.account.databinding.ActivitySelectBinding
import com.example.account.vo.Account
import java.text.SimpleDateFormat

class SelectActivity : AppCompatActivity() {

    val binding by lazy { ActivitySelectBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.selBtn.setOnClickListener {
            var db = DBhelper.getInstance(this,"account.db")
            var date = String.format("%d-%02d-%02d", binding.selectCalendar.year, binding.selectCalendar.month+1,binding.selectCalendar.dayOfMonth)
            var list = db.select(date)
            binding.textViewResVal.text = "${list[0].seq}, ${list[0].icOg}, ${list[0].title}, ${list[0].date}, ${list[0].Amount}, ${list[0].memo}"
        }

    }
}