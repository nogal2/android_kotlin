package com.example.account


import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import com.example.account.databinding.ActivityInsertBinding
import com.example.account.vo.Account
import java.text.SimpleDateFormat
import java.util.*

class InsertActivity : AppCompatActivity() {

    val binding by lazy { ActivityInsertBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var radio = ""

        binding.radioGroup.setOnCheckedChangeListener { _, checkedid ->
            when(checkedid) {
                R.id.incomeRadio -> radio = binding.incomeRadio.text.toString()
                R.id.expenseRadio -> radio = binding.expenseRadio.text.toString()
            }
            Log.d("라디오", radio)
        }



        binding.addBtn.setOnClickListener {
            var date = String.format("%d-%02d-%02d", binding.insertDate.year, binding.insertDate.month+1, binding.insertDate.dayOfMonth)
            val account = Account(0,
                                    radio,
                                    binding.editTextTitle.text.toString(),
                                    date,
                                    binding.editTextAmount.text.toString().toInt(),
                                    binding.editTextMemo.text.toString()
            )

            var db = DBhelper.getInstance(this, "account.db")
            db.insert(account)

            Toast.makeText(this@InsertActivity, "입력하신 내용이 저장되었습니다.", Toast.LENGTH_SHORT).show()

            binding.editTextMemo.setText("")
            binding.editTextAmount.setText("")
            binding.editTextTitle.setText("")

        }

        binding.returnBtnIns.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

}