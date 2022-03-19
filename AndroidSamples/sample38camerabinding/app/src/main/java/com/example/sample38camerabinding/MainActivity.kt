package com.example.sample38camerabinding

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sample38camerabinding.databinding.ActivityMainBinding
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // CAMERA, STORAGE 권한 처리에 필요한 변수
    val CAMERA = arrayOf(Manifest.permission.CAMERA)
    val STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val CAMERA_CODE = 98
    val STORAGE_CODE = 99


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //카메라
        binding.camera.setOnClickListener {
            CallCamera()
        }

        //사진 저장
        binding.picture.setOnClickListener {
            GetAlbum()
        }
    }

    // 요청 권한
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            CAMERA_CODE -> {
                for (grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "카메라 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                    }
                }
            }
            STORAGE_CODE -> {
                for (grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "저장소 권한을 승인해주세요", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun checkPermission(permissions: Array<out String>, type:Int):Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(permission in permissions) {
                if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions,type)
                    return false
                }
            }
        }
        return true
    }

    fun CallCamera() {
        if(checkPermission(CAMERA, CAMERA_CODE) && checkPermission(STORAGE,STORAGE_CODE)) {
            val itt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(itt, CAMERA_CODE)
        }
    }

    fun saveFile(fileName:String, mimeType:String, bitmap:Bitmap):Uri? {
        var CV = ContentValues()

        // MediaStore 에 파일명, mimeType 을 지정, MediaStore는 외부저장소를 관리하는 데이터베이스
        CV.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        CV.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        Log.d ("CV 값:", "$CV")
        // 안정성 검사
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            CV.put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        //mediaStore 에 파일을 저장
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, CV)
        Log.d("저장할때 uri 값: ", "$uri")
        if(uri != null) {
            var scriptor = contentResolver.openFileDescriptor(uri, "w")

            val fos = FileOutputStream(scriptor?.fileDescriptor)

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.close()

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                CV.clear()

                //IS_PENDING을 초기화
                CV.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri,CV,null,null)
            }

        }
        return uri
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                CAMERA_CODE -> {
                    if(data?.extras?.get("data") != null) {
                        val img = data?.extras?.get("data") as Bitmap
                        val uri = saveFile(RandomFileName(), "image/jpeg", img)
                        binding.avatars.setImageURI(uri)
                        Toast.makeText(this, "$uri", Toast.LENGTH_LONG).show()
                        Log.d("이미지 띄울때 uri 값: ", "$uri")
                    }
                }

                STORAGE_CODE -> {
                    val uri = data?.data
                    Log.d("STORAGE_CODE uri 값: ", "$uri")
                    binding.avatars.setImageURI(uri)
                }
            }
        }
    }

    // 파일명을 현재 날짜로 저장
    fun RandomFileName(): String {  // RandomFileName() 은 사진을 여러장 찍을 경우 파일명이 중복 될 수 있기 때문에.
        val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
        return fileName
    }

    // 갤러리 취득
    fun GetAlbum() {
        if(checkPermission(STORAGE, STORAGE_CODE)) {
            val itt = Intent(Intent.ACTION_PICK)
            itt.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(itt, STORAGE_CODE)
        }
    }
}