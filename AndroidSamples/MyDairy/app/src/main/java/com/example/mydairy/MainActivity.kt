package com.example.mydairy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydairy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val STORAGE_CODE = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkPermission(STORAGE, STORAGE_CODE)
        var db = DBhelper.getInstance(this,"myDairy.db")
        var list = db.viewSelect()

        val mAdapter = MyAdapter(this, list)
        binding.recyclerView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layout
        binding.recyclerView.setHasFixedSize(true)

        binding.addBtn.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

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
}