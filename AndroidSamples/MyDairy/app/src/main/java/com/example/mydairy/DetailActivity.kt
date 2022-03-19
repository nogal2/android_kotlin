package com.example.mydairy

import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mydairy.databinding.ActivityDetailBinding
import java.io.File
import java.io.IOException

class DetailActivity : AppCompatActivity() {
    companion object {
        var lat:Double = 0.0
        var lon:Double = 0.0
    }
    val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DataVo>("data")

        var file = File(data?.imagePath)
        if(file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            binding.detailImageView.setImageBitmap(bitmap)
        }

        binding.detailDateTextView.text = data?.date
        binding.detailContentTextView.text = data?.content

        // 주소를 위도,경도로 변환해서 프래그먼트로 값 넘기기
        val geocoder = Geocoder(this)
        var list:List<Address>? = null
        val str = data?.address
        try{
            list = geocoder.getFromLocationName(str, 10)
        }catch (e:IOException){}

        if(list != null) {
            if(list!!.isEmpty()) {
                Toast.makeText(this,"주소없음", Toast.LENGTH_SHORT).show()
            } else {
                val mapsFragment = MapsFragment(this)
                lat = list[0].latitude
                lon = list[0].longitude
                /*val bundle = Bundle()
                bundle.putDouble("lat", list[0].latitude)
                bundle.putDouble("lon", list[0].longitude)
                Log.d("번들: ", "${list[0].latitude}, ${list[0].longitude}")*/
                val fm = supportFragmentManager.beginTransaction()
                fm.add(R.id.content, mapsFragment)
                fm.commit()
            }
        }

    }

}