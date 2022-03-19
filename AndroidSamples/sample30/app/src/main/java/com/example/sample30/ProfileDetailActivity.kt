package com.example.sample30

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        // 짐을 푸는것.
        val data = intent.getParcelableExtra<DataVo>("data")
        println(data?.name + " " + data?.id)

        val imageView = findViewById<ImageView>(R.id.img_profile)
        val userId = findViewById<TextView>(R.id.user_id)
        val userName = findViewById<TextView>(R.id.user_name)
        val userPay = findViewById<TextView>(R.id.user_pay)

        //imageView.setImageResource(R.drawable.kim) 이건 사용 못함 왜냐하면 이미지가 문자열로 넘어오기 때문에.
        //imageView 에 data?.photo 명의 이미지를 드로잉해라
        Glide.with(this).load(getImage(data?.photo)).into(imageView)    // 이미지 불러올때는 이게 폭넓게 사용할때 좋음


        userId.text = data?.id
        userName.text = data?.name
        userPay.text = data?.pay.toString()
    }

    //문자열로 넘어온 이미지를 받아서 화면에 띄워주기 위한 함수`
    fun getImage(imageName:String?): Int {
        return resources.getIdentifier(imageName, "drawable", packageName)
    }

}