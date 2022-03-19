package com.example.membership

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.membership.databinding.ActivityAllPrintBinding

class allPrintActivity : AppCompatActivity() {

    val binding by lazy { ActivityAllPrintBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var str = intent.getStringArrayListExtra("mem") as ArrayList<Member>
        for (i in 0 until str.size) {
            binding.apResText.text = "${str[i]}"
        }


    }
}