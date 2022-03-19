package com.example.sample40geocoding

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sample40geocoding.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //여행 -> 사진: 위치 -> 주소 -> 위도,경도도
        val geocoder:Geocoder = Geocoder(this)

        // 변환: 위도, 경도 -> 주소
        binding.addrBtn.setOnClickListener {
            var list:List<Address>? = null

            try {
                val d1: Double = binding.latText.text.toString().toDouble()
                val d2: Double = binding.lonText.text.toString().toDouble()

                list = geocoder.getFromLocation(d1, d2, 10)   // 얻어올 값의 갯수가 10개
            }catch (e:IOException) {
                Log.d("위도/경도", "입출력 오류")
            }
            if(list != null) {
                if(list.isEmpty()) {    // 주소가 만료되어서 등 없을때
                    binding.textView.text = "해당되는 주소는 없습니다."
                } else {    // 정상적인 주소가 나왔을때
                    binding.textView.text = list[0].toString()
                }

            }
        }

        binding.mapBtn1.setOnClickListener {
            val d1: Double = binding.latText.text.toString().toDouble()
            val d2: Double = binding.lonText.text.toString().toDouble()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$d1,$d2"))
            startActivity(intent)
        }

        //주소 -> 위도,경도
        binding.latBtn.setOnClickListener {
            var list:List<Address>? = null
            val str = binding.addrText.text.toString()

            try {
                list = geocoder.getFromLocationName(str, 10)
            }catch (e:IOException) {}

            if(list != null) {
                if(list!!.isEmpty()) {
                    binding.textView.text = "해당되는 주소는 없습니다."
                } else {
                    // binding.textView.text = list!![0].toString()
                    binding.textView.text = list!![0].latitude.toString()+ " " + list!![0].longitude.toString()
                }
            }

        }

        binding.mapBtn2.setOnClickListener {
            var list:List<Address>? = null
            val str = binding.addrText.text.toString()

            try {
                list = geocoder.getFromLocationName(str, 10)
            }catch (e:IOException) {}

            if(list != null) {
                if(list!!.isEmpty()) {
                    binding.textView.text = "해당되는 주소는 없습니다."
                }else {
                    val addr = list!![0]
                    val lat = addr.latitude
                    val lon = addr.longitude
                    val geo = String.format("geo:%f,%f", lat, lon)
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geo))
                    startActivity(intent)
                }
            }

        }
    }
}