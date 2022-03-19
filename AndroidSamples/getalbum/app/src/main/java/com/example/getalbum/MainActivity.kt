package com.example.getalbum

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.getalbum.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val STORAGE_CODE = 99

    val LOAD_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var imgFile = File("\\DCIM\\Screenshots\\scr.jpg")
        if(imgFile.exists()) {
            var bitmap:Bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            binding.avatars.setImageBitmap(bitmap)
        }

    }
/*

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {


            try {
                //val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,currentImageUrl)
                var bitmap:Uri? = Uri.parse("file:///" + Environment.getExternalStorageDirectory()+ "내 PC\\Galaxy Tab A (8.0\", 2019)\\Tablet\\DCIM\\Screenshots")
                binding.avatars.setImageURI(bitmap)
            }catch (e:IOException) {
                e.printStackTrace()
            }
        }else {
            Log.d("ActivityResult", "something wrong")
        }
    }
*/
    fun checkPermission(permissions:Array<out String>, type:Int):Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(permission in permissions) {
                if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions,type)
                    return false
                }
            }
        }
        return true
    }


    fun getAlbum() {
        if(checkPermission(STORAGE, STORAGE_CODE)) {
            val itt = Intent(Intent.ACTION_PICK)
            itt.type = MediaStore.Images.Media.CONTENT_TYPE
            startActivityForResult(itt, STORAGE_CODE)
        }
    }

    fun loadGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, LOAD_GALLERY)
    }


}