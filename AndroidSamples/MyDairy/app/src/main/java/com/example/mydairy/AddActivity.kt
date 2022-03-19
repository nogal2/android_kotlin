package com.example.mydairy

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mydairy.databinding.ActivityAddBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.log

class AddActivity : AppCompatActivity() {

    val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }

    // 카메라, 저장소 권한
    val CAMERA = arrayOf(Manifest.permission.CAMERA)
    val STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    // 카메라, 저장소 코드번호
    val CAMERA_CODE = 98
    val STORAGE_CODE = 99

    // 위치 권한 허가
    lateinit var locationPermission: ActivityResultLauncher<Array<String>>

    // 위치 서비스가 gps를 사용해서 위치를 확인
    lateinit var fusedLocationClient: FusedLocationProviderClient

    // 위치 값 요청에 대한 갱신 정보를 받는 변수
    lateinit var locationCallback: LocationCallback

    var Path = ""
    var lat:Double = 0.0
    var lon:Double = 0.0
    var location = ""


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions() ){ results->
            if(results.all { it.value }){
            } else{ // 문제가 발생했을 때
                Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show()
            }
        }

        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        locationCallback = object: LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.let {
                    for(location in it.locations) {
                        lat = location.latitude
                        lon = location.longitude
                        Log.d("위치1","$lat $lon")
                    }
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())



        // 카메라
        binding.camera.setOnClickListener {
            CallCamera()
        }

        // 저장
        binding.saveBtn.setOnClickListener {

            //위도/경도를 주소로 반환
            addrConvert()
            /*val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val currentDate = current.format(formatter)*/

            val imagePath = Path
            val content = binding.editTextContent.text.toString()
            location

            Log.d("저장값", "$imagePath, $location, $content")
            var db = DBhelper.getInstance(this, "myDairy.db")
            val dataVo = DataVo(0, imagePath,location, content, "")
            db.insert(dataVo)
            Toast.makeText(this,"저장되었습니다", Toast.LENGTH_SHORT).show()

        }

    }

    // 카메라, 저장소 권한 승인 요청
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
                for(grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "저장소 권한을 승인해 주세요", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    // 다른 권한들도 확인
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

    // 카메라 촬영
    fun CallCamera() {
        if(checkPermission(CAMERA,CAMERA_CODE) && checkPermission(STORAGE,STORAGE_CODE)) {
            val itt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(itt, CAMERA_CODE)
        }
    }

    fun saveFile(fileName:String, mimeType:String, bitmap: Bitmap): Uri? {
        var CV = ContentValues()

        // MediaStore에 파일명, mimeType(파일 유형)을 지정.
        CV.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        CV.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        //안정성 검사
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            CV.put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        //MediaStore 에 파일 저장
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, CV)
        if(uri != null) {
            var scriptor = contentResolver.openFileDescriptor(uri, "w")

            var fos = FileOutputStream(scriptor?.fileDescriptor)

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.close()

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                CV.clear()

                CV.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri,CV,null,null)
            }
        }
        return uri

    }

    // 촬영후 동작
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                CAMERA_CODE -> {
                    if(data?.extras?.get("data") != null) {
                        val img = data?.extras?.get("data") as Bitmap
                        val uri = saveFile(RandomFileName(), "image/jpeg", img)
                        binding.imageView.setImageURI(uri)
                        Toast.makeText(this, "${uri}", Toast.LENGTH_LONG).show()
                        Path = getPath(uri)
                        binding.imgPath.text = Path

                    }
                }

                STORAGE_CODE -> {
                    val uri = data?.data
                    binding.imageView.setImageURI(uri)
                    Toast.makeText(this, "경로: $uri", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    // 파일명을 현재 날짜로 저장
    fun RandomFileName(): String {
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


    // 위도,경도 -> 주소
    fun addrConvert() {
        val geocoder = Geocoder(this)
        var list:List<Address>? = null
        try {
            val d1:Double = lat
            val d2:Double = lon

            list = geocoder.getFromLocation(d1,d2,10)
        }catch (e:IOException) {}

        if(list != null) {
            if(list.isEmpty()) {
                Log.d("주소 없음", "해당 주소가 없습니다.")
            } else {
                location = list[0].getAddressLine(0)
            }
        }

    }


}