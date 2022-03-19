package com.example.rullet

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.rullet.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rulletGame()

        binding.restartBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun rulletGame() {
        var childLayout:LinearLayout? =null
        var btnCount = intent.getIntExtra("gameNumber",0)
        val range = (1..btnCount)
        val random = range.random()

        val btnParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        btnParams.weight = 1.0f

        for(i in 0 until btnCount) {
            if(i%3 == 0) {
                childLayout = LinearLayout(this)
                childLayout.orientation = LinearLayout.HORIZONTAL
                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100)
                childLayout.layoutParams = layoutParams
            }

            val normalBtn = Button(this).apply {
                var count = 0
                text = (i+1).toString()
                layoutParams = btnParams
                id = i
                setOnClickListener{
                    if(count == 0) {
                        if(text.toString().toInt() == random) {
                            Log.d("빙고", "빙고${random}")
                            text = "bingo"
                            setBackgroundColor(Color.YELLOW)

                        } else {
                            Log.d("세이프", "세이프${random}")
                            text = "safe"
                            setBackgroundColor(Color.BLUE)
                            setTextColor(Color.WHITE)
                        }
                    }
                    count++
                }

            }
            childLayout?.addView(normalBtn)

            if(i%3==2 || i == (btnCount -1)) {
                binding.linearLayout.addView(childLayout)
            }

        }
    }

}
