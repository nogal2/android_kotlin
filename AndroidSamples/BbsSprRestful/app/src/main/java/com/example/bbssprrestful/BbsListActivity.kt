package com.example.bbssprrestful

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bbssprrestful.dao.BbsDao
import com.example.bbssprrestful.databinding.ActivityBbsListBinding
import com.example.bbssprrestful.databinding.ActivityMainBinding

class BbsListActivity : AppCompatActivity() {

    val binding by lazy { ActivityBbsListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bbsList = BbsDao.getInstance().getBbsList()

        val mAdapter = CustomAdapter(this, bbsList)
        binding.recyclerView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layout
        binding.recyclerView.setHasFixedSize(true)

        binding.addBbsBtn.setOnClickListener {
            startActivity(Intent(this,BbsWriteActivity::class.java))
        }
    }
}