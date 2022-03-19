package com.example.sample37

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        var childLayout:LinearLayout? = null

        var btnCount = 7

        for(i in 0 until btnCount) {
            if(i % 3 ==0) {
                childLayout = LinearLayout(this)    // LinearLayout이 하나 더 생김(horizontal)
                childLayout.orientation = LinearLayout.HORIZONTAL
                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100)
                childLayout.layoutParams = layoutParams
            }

            val btnParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            btnParams.weight = 1.0f

            val normalBtn = Button(this).apply {
                text = (i+1).toString()
                layoutParams = btnParams
                id = i

                setOnClickListener{
                    val myToast = Toast.makeText(this@MainActivity, "${id+1}버튼 클릭", Toast.LENGTH_SHORT)
                    myToast.show()
                }

            }
            /*
            위에걸 이렇게 할 수도 있음.
            normalBtn.text = (i+1).toString()
            normalBtn.layoutParams = btnParams
            normalBtn.id = i
            normalBtn.setOnClickListener
            */
            childLayout?.addView(normalBtn)

            if(i % 3 == 2 || i == (btnCount - 1)) { // 이 조건문을 안하면 3개가 안될시 생성이 안된다.
                linearLayout.addView(childLayout)
            }

        }

    }
}