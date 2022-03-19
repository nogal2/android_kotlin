package com.example.membership

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.membership.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    val binding by lazy { ActivityInsertBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener {
            val mem = Member(0,
                                binding.editTextName.text.toString(),
                                binding.editTextAge.text.toString().toInt(),
                                binding.editTextRelationship.text.toString(),
                                binding.editTextJob.text.toString(),
                                binding.editTextAddress.text.toString(),
                                binding.editTextPhone.text.toString())

            var db = DBhelper(this, "member.db")
            var database = db.writableDatabase
            db.insert(mem)

            Toast.makeText(this@InsertActivity, "회원 정보가 추가되었습니다.", Toast.LENGTH_SHORT).show()

            binding.editTextName.setText("")
            binding.editTextAge.setText("")
            binding.editTextAddress.setText("")
        }

        binding.returnBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}