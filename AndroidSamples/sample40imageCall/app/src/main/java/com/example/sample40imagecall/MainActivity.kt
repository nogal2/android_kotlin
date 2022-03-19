package com.example.sample40imagecall

import android.Manifest
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File

class MainActivity : AppCompatActivity() {

    // 권한 허가
    lateinit var locationPermission: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            if(!results.all {it.value}) {
                Toast.makeText(this,"권한 승인 필요", Toast.LENGTH_SHORT).show()
            }
        }

        // 권한 허가를 물어보게 됨
        locationPermission.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )

        val imageView = findViewById<ImageView>(R.id.imageView)
        val file:File = File("/storage/emulated/0/Pictures/20220217092113.jpg")
        val fExist = file.exists()  // true / false 로 넘어옴
        if(fExist) {
            Log.d("", "이미지 파일 있음")

            val myBitmap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/20220217092113.jpg")
            imageView.setImageBitmap(myBitmap)
        }else {
            Log.d("", "이미지 파일 없음")
        }

    }
}