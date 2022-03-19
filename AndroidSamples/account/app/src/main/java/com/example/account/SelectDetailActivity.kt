package com.example.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.account.databinding.ActivitySelectDetailBinding
import com.example.account.vo.Account

class SelectDetailActivity : AppCompatActivity() {
    val binding by lazy { ActivitySelectDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Account>("data")
        Log.d("데이터", "${data?.title}")

        binding.textViewDeTitCont.text = data?.title
        binding.textViewDeDateCont.text = data?.date
        binding.textViewDeAmountCont.text = data?.Amount.toString()
        binding.textViewDeMemoCont.text = data?.memo
        binding.textViewDeIcgoCont.text = data?.icOg
    }
}