package com.example.bbssprrestful

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.bbssprrestful.dao.BbsDao
import com.example.bbssprrestful.dao.MemberDao
import com.example.bbssprrestful.databinding.ActivityBbsWriteBinding
import com.example.bbssprrestful.databinding.ActivityMainBinding
import com.example.bbssprrestful.dto.BbsDto

class BbsWriteActivity : AppCompatActivity() {

    val binding by lazy { ActivityBbsWriteBinding.inflate(layoutInflater) }
    val id = MemberDao.getInstance().dto.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.writeIdTextView.text = id

        binding.addWriteBtn.setOnClickListener {
            val title = binding.writeTitleEditText.text.toString()
            val content = binding.writeContentEditText.text.toString()
            println(id + title + content)
            val dto = BbsDto(0,id,0,0,0,title,content,"",0,0)
            println(dto.toString())
            val res = BbsDao.getInstance().addWrite(dto)
            if( res == "yes") {
                AlertDialog.Builder(this@BbsWriteActivity)
                    .setTitle("알림")
                    .setMessage("작성이 완료되었습니다.")
                    .setCancelable(false)
                    .setNeutralButton("닫기", DialogInterface.OnClickListener { dialog, which ->  }).show()

                startActivity(Intent(this, BbsListActivity::class.java))
            } else {
                AlertDialog.Builder(this@BbsWriteActivity)
                    .setTitle("알림")
                    .setMessage("작성 실패")
                    .setCancelable(false)
                    .setNeutralButton("닫기", DialogInterface.OnClickListener { dialog, which ->  }).show()
            }
        }


    }
}