package com.example.sample41googlemapfragment

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sample41googlemapfragment.databinding.ActivityMainBinding
import com.google.android.gms.maps.MapFragment
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.content, MapsFragment(this))
        fragmentTransaction.commit()

        val geocoder:Geocoder = Geocoder(this)

        binding.button.setOnClickListener {
            var list:List<Address>? = null
            val str = binding.editTextTextPersonName.text.toString()

            try{
                list = geocoder.getFromLocationName(str, 10)
            }catch (e:IOException) {}

            if(list != null) {
                if(list!!.isEmpty()) {
                    Toast.makeText(this,"주소없음", Toast.LENGTH_SHORT).show()
                }else {
                    binding.editTextTextPersonName2.setText(list[0].latitude.toString())
                    binding.editTextTextPersonName3.setText(list[0].longitude.toString())

                    val lat = list[0].latitude
                    val lon = list[0].longitude
                    val geo = String.format("geo:%f,%f", lat, lon)
                    val bundle = Bundle()
                    bundle.putDouble("lat", lat)
                    bundle.putDouble("lon", lon)
                    Log.d("번들: ","${bundle.getDouble("lat")}, ${bundle.getDouble("lon")}")

                    val mapsFragment = MapsFragment(this)
                    mapsFragment.arguments = bundle
                    Log.d("프래그", "${mapsFragment.arguments!!.getDouble("lat")}")
                    val btn = supportFragmentManager.beginTransaction()
                    btn.replace(R.id.content, mapsFragment)
                    btn.commit()


                }
            }


        }

    }
}