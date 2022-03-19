package com.example.bbssprrestful

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.bbssprrestful.dao.MemberDao
import com.example.bbssprrestful.databinding.ActivityMainBinding
import com.example.bbssprrestful.dto.MemberDto

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val id = binding.idEditText.text.toString()
            val pw = binding.pwEditText.text.toString()
            val dto = MemberDto(id, pw,"","",3)
            val login = MemberDao.getInstance().login(dto)

            //Log.d("체크" ,"${login.auth}+ ${login.id} + ${login.pwd} + ")
            if(login.id == id && id != "") {
                startActivity(Intent(this, BbsListActivity::class.java))
            } else {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("로그인 오류")
                    .setMessage("비밀번호 또는 아이디가 다릅니다.")
                    .setCancelable(false)
                    .setNeutralButton("닫기", DialogInterface.OnClickListener { dialog, which ->  }).show()
            }

        }

    }
}