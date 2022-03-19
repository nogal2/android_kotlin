package com.example.sample05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*버튼 클릭한 부분이 호출되는 함수*/
    override fun onClick(view: View?) {
        println("onClick")
        val myToast = Toast.makeText(this.applicationContext, "Toast 메시지", Toast.LENGTH_SHORT)
        myToast.show()

        when(view?.id) {
            R.id.btn ->{// 버튼을 눌렀을 때
                var pride = findViewById<ImageView>(R.id.pride)
                pride.visibility = if(pride.visibility == View.VISIBLE) {   // 만약 view에 pride가 활성화가 되어있을때
                    View.INVISIBLE  // 이미지 숨김
                }else{
                    View.VISIBLE    // 이미지 노출
                }
            }
        }

    }

}