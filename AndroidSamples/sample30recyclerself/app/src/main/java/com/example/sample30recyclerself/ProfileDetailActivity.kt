package com.example.sample30recyclerself

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        val data = intent.getParcelableExtra<DataVo>("data")
        println(data?.name + " " + data?.id)

        val imageView = findViewById<ImageView>(R.id.img_profile)
        val userId = findViewById<TextView>(R.id.user_id)
        val userName = findViewById<TextView>(R.id.user_name)
        val userPay = findViewById<TextView>(R.id.user_pay)


        userId.text = data?.id
        userName.text = data?.name
        userPay.text = data?.pay.toString()
        Glide.with(this).load(getImage(data?.photo)).into(imageView)


    }

    fun getImage(imageName:String?) : Int {
        return resources.getIdentifier(imageName, "drawable", packageName)
    }

}