package com.example.sample38camera

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    // CAMERA, STORAGE 권한 처리에 필요한 변수
    val CAMERA = arrayOf(Manifest.permission.CAMERA)
    val STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val CAMERA_CODE = 98
    val STORAGE_CODE = 99


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 카메라
        val camera = findViewById<Button>(R.id.camera)
        camera.setOnClickListener {
            CallCamera()
        }

        // 사진 저장
        val picture = findViewById<Button>(R.id.picture)
        picture.setOnClickListener {
            GetAlbum()
        }
    }

    // 요청 권한(카메라 권한, 저장소 권한)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_CODE -> {
                for (grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "카메라 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                    }
                }
            }
            STORAGE_CODE -> {
                for(grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "저장소 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    // 다른 권한 들도 확인이 가능하도록
    fun checkPermission(permissions: Array<out String>, type:Int):Boolean {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(permission in permissions) {
                if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,permissions,type)
                    return false
                }
            }
        }
        return true
    }

    // 카메라 촬영 - 권한 처리
    fun CallCamera() {
        if(checkPermission(CAMERA, CAMERA_CODE) && checkPermission(STORAGE, STORAGE_CODE)) {    // 카메라 권한, 저장소 권한 둘다 허용할 경우
            val itt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(itt, CAMERA_CODE)
        }
    }

    // 촬영된 사진 저장
    fun saveFile(fileName:String, mimeType:String, bitmap: Bitmap):Uri? {
        var CV = ContentValues()

        // MediaStore 에 파일명, mimeType 을 지정, MediaStore는 외부저장소를 관리하는 데이터베이스
        CV.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        CV.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        // 안정성 검사
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {    // SDK 버전이 현재 버전보다 같거나 높았을때
            CV.put(MediaStore.Images.Media.IS_PENDING, 1)       // 다른 곳에서 현재 사용하려는 데이터 사용 요청이 오면 이를 무시하도록 하는 것.
        }

        // MediaStore 에 파일을 저장
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, CV)
        if(uri != null) {
            var scriptor = contentResolver.openFileDescriptor(uri, "w")     // 이것을 통해 파일을 읽거나 쓸 수 있게된다.

            val fos = FileOutputStream(scriptor?.fileDescriptor)

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)       // FileOutputStream으로 Bitmap파일을 저장한다.
            fos.close()                                                       // 100은 압축이며 이 수치가 클 수록 화질이 좋아진다. 그만큼 용량 증가.

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                CV.clear()

                // IS_PENDING 을 초기화
                CV.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri, CV, null, null)
            }
        }
        return uri
    }

    // 촬영 후 작동
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageView = findViewById<ImageView>(R.id.avatars)

        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                CAMERA_CODE -> {
                    if(data?.extras?.get("data") != null) {
                        val img = data?.extras?.get("data") as Bitmap
                        val uri = saveFile(RandomFileName(), "image/jpeg", img)
                        imageView.setImageURI(uri)
                        Toast.makeText(this, "${uri}", Toast.LENGTH_LONG).show()    // 경로보려고 임시로작성
                        println("이미지 경로: $uri")
                        println("실제 이미지 경로: ${getPath(uri)}")
                    }
                }
                STORAGE_CODE -> {       // 위에서 불러온 목록중 선택된 사진을 불러와 imageView에 표시
                    val uri = data?.data
                    imageView.setImageURI(uri)
                }
            }
        }

    }

    // 파일명을 현재 날짜로 저장
    fun RandomFileName(): String {   // RandomFileName() 은 사진을 여러장 찍을 경우 파일명이 중복 될 수 있기 때문에.
        val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
        return fileName
    }

    // 갤러리 취득
    fun GetAlbum() {
        if(checkPermission(STORAGE, STORAGE_CODE)) {
            val itt = Intent(Intent.ACTION_PICK)                // MediaStore 에서 지정한 종류의 데이터를 목록에서 선택할 수 있도록 한다.
            itt.type = MediaStore.Images.Media.CONTENT_TYPE     // Images를 지정하였으므로 Image만 가져와 보여주게 된다.
            startActivityForResult(itt, STORAGE_CODE)
        }
    }

    // Uri String으로 변환(태블릿 안에 있는 경로 뿌리기)
    fun getPath(uri: Uri?): String {
        val projection = arrayOf<String>(MediaStore.Images.Media.DATA)
        val cursor: Cursor = managedQuery(uri, projection, null, null, null)
        startManagingCursor(cursor)
        val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }


}