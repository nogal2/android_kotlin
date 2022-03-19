package com.example.sample14self

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.sample14self.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSpinnerFruit()
        setupSpinnerHandler()

    }

    val fruit = arrayOf("과일선택", "사과", "배", "바나나", "포도")


    fun setupSpinnerFruit() {
        val adapter = ArrayAdapter(this, R.layout.item_spinner, fruit)
        binding.spinner.adapter = adapter


    }

    fun setupSpinnerHandler() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.textView.text = "선택됨: $position ${fruit[position]}"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

}