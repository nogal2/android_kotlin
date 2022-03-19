package com.example.sample

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) } // gradle을 객체화 (buildFeatures{viewBinding true })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //  setContentView(R.layout.activity_main)
        /* 첫번째 방식
        setContentView(binding.root)    // 변수 binding은 여기 와서 초기화가 됨.(lazy가 있기 때문에)

        binding.btnSay.setOnClickListener { //버튼을 클릭했을때, 이 부분에서 실행 된다.
            Log.i(ContentValues.TAG, "btnSay 클릭했습니다.")
            println("btnSay 클릭했습니다")

            binding.textSay.text = "Hello Android!!"
        }
        */
        setContentView(R.layout.activity_main)
        val textSay = findViewById<TextView>(R.id.textSay)
        val btnSay = findViewById<Button>(R.id.btnSay)

        btnSay.setOnClickListener {
            textSay.text = "Android World!!!"

            // Toast
            val toast = Toast.makeText(this.applicationContext,"버튼 클릭", Toast.LENGTH_SHORT)
            toast.show()

            AlertDialog.Builder(this@MainActivity)
                .setTitle("welcome")
                .setMessage("어서오세요!")
                .setCancelable(false)
                .setNeutralButton("닫기", DialogInterface.OnClickListener{dialog, which->
                    // 닫음 버튼 클릭시!
                }).show()

        }

        Log.i(ContentValues.TAG, "onCreate 실행")
    }

    override fun onStart() {
        super.onStart()
        Log.i(ContentValues.TAG, "onStart 실행")
    }

    override fun onResume() {
        super.onResume()
        Log.i(ContentValues.TAG, "onResume 실행")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(ContentValues.TAG, "onRestart 실행")
    }

    override fun onPause() {
        super.onPause()
        Log.i(ContentValues.TAG, "onPause 실행")
    }

    override fun onStop() {
        super.onStop()
        Log.i(ContentValues.TAG, "onStop 실행")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(ContentValues.TAG, "onDestroy 실행")
    }
}