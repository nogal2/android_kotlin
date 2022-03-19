package com.example.sample36sqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sample36sqllite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var res:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        var dbHelper = DBHelper(this, "mydb.db", null, 1)

        var insertBtn = findViewById<Button>(R.id.insertBtn)
        val editInsert = findViewById<EditText>(R.id.editInsert)

        var database = dbHelper.writableDatabase

        insertBtn.setOnClickListener {
            val txt = editInsert.text

            // DB에 들어감
            dbHelper.insert(database, txt.toString())
        }

        binding.deleteBtn.setOnClickListener{
            val txt = binding.editDelete.text
            dbHelper.delete(database, txt.toString())
        }

        binding.selectBtn.setOnClickListener{
            val txt = binding.editSelect.text
            res = dbHelper.select(database, txt.toString())
            Toast.makeText(this,"$res 이 있습니다.",Toast.LENGTH_SHORT).show()
            binding.selectText.text = res
        }

        binding.updateBtn.setOnClickListener{
            val txt = binding.editUpdate.text
            val sel = binding.selectText.text
            dbHelper.update(database, txt.toString(), sel.toString())
            Toast.makeText(this,"수정되었습니다.",Toast.LENGTH_SHORT).show()
        }


    }
}