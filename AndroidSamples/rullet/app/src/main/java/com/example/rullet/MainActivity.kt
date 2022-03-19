package com.example.rullet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.rullet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var gameNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSpinner()
        setupSpinnerHandler()
        binding.setBtn.setOnClickListener{
            Toast.makeText(this@MainActivity, "${gameNumber}개의 룰렛게임. Start로 게임시작", Toast.LENGTH_SHORT).show()
        }

        binding.startBtn.setOnClickListener{
            var secView = Intent(this, SecondActivity::class.java)
            secView.putExtra("gameNumber", gameNumber)
            startActivity(secView)
        }

    }

    val number = arrayOf(1,2,3,4,5,6,7,8,9)


    fun setupSpinner() {
        val adapter = ArrayAdapter(this, R.layout.item_spinner, number)
        binding.spinner.adapter = adapter
    }

    fun setupSpinnerHandler() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                gameNumber = number[pos]

                //Toast.makeText(this@MainActivity, "${number[pos]}선택함", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

}