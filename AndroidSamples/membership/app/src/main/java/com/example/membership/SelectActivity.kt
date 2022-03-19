package com.example.membership

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.membership.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {

    val binding by lazy { ActivitySelectBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var db = DBhelper.getInstance(this,"member.db")


        binding.btnSelect.setOnClickListener {
            var name:String = binding.selEditTextName.text.toString()
            var database = DBhelper.getInstance(this,"member.db").writableDatabase
            var res:String = db.select(database, name)
            binding.selResTextView.text = res
            binding.selEditTextName.setText("")
        }

        binding.selReturnBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}