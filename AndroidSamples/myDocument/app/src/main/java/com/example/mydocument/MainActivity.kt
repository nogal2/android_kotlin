package com.example.mydocument

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydocument.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var storagePermission: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
/*

        storagePermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {results ->
            if(!results.all {it.value}) {
                Toast.makeText(this, "권한 승인 필요", Toast.LENGTH_SHORT).show()
            }
        }
*/


/*

        var db = DBhelper.getInstance(this,"myDocument.db")
        var list = db.viewSelect()

        val mAdapter = MyAdapter(this, list)
        binding.recyclerView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layout
        binding.recyclerView.setHasFixedSize(true)


        binding.addBtn.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
*/


    }
}