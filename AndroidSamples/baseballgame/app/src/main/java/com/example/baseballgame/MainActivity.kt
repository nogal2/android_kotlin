package com.example.baseballgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseballgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var random:Int = (Math.random() * 100).toInt() +1
        var uNum:Int

        binding.btnEnter.setOnClickListener {
            uNum = binding.userNum.text.toString().toInt()
            if(uNum > random) {
                binding.response.text = "너무 큽니다."
            }
            else if(uNum < random){
                binding.response.text = "너무 작습니다."
            } else {
                binding.response.text = "정답입니다."
            }
        }

        binding.btnReset.setOnClickListener {
            random = (Math.random() * 100).toInt() +1
            binding.response.text = "환영합니다."
        }

        binding.btnStart.setOnClickListener {
            binding.response.text = random.toString()
        }


    }



}