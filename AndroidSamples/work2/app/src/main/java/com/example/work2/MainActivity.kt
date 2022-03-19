package com.example.work2

import android.content.ContentValues
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import com.example.work2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val age = arrayOf("나이 선택", "10대 미만", "10대", "20대", "30대", "40대", "50대 이상")
    var chk = ""
    var selAge = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSpinnerAge()
        setupSpinnerHandler()

        var rdoVal = ""


            binding.rg.setOnCheckedChangeListener {_, checkedid ->
            when(checkedid) {
                R.id.rdo1 -> rdoVal = binding.rdo1.text as String
                R.id.rdo2 -> rdoVal = binding.rdo2.text as String
                R.id.rdo3 -> rdoVal = binding.rdo3.text as String
            }
        }

        binding.chk1.setOnCheckedChangeListener(checkListener)
        binding.chk2.setOnCheckedChangeListener(checkListener)

        binding.result.setOnClickListener {
            Log.i(ContentValues.TAG, "result 클릭")
            AlertDialog.Builder(this@MainActivity)
                .setTitle("결과")
                .setMessage("${binding.inputName.text}, $rdoVal, $chk, ${binding.inputOpn.text}, $selAge"

                )
                .setCancelable(false)
                .setNeutralButton("닫기", DialogInterface.OnClickListener{_, _ ->}).show()
            chk = ""
        }

    }

    val checkListener by lazy {
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                when(buttonView.id) {
                    R.id.chk1 -> chk += (" " +binding.chk1.text as String)
                    R.id.chk2 -> chk += (binding.chk2.text as String)
                }
            } else {

            }
        }
    }

    fun setupSpinnerAge() {
        val adapter = ArrayAdapter(this, R.layout.item_spinner, age)
        binding.spinner.adapter = adapter

    }

    fun setupSpinnerHandler() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                selAge = "${age[pos]}"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }


}