package com.example.sample41googlemapfragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sample41googlemapfragment.databinding.FragmentMapsBinding
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment(val activity:Activity) : Fragment(), OnMapReadyCallback {   // OnMapReadyCallback -> 실시간으로 위치 바뀌는걸 하기 위해서



    lateinit var locationPermission: ActivityResultLauncher<Array<String>>

    private lateinit var mMap: GoogleMap    // 맵을 뿌리기 위해서 필요

    // 위치 서비스가 GPS를 사용해서 위치를 확인
    lateinit var fusedLocationClient: FusedLocationProviderClient

    // 위치 값 요청에 대한 갱신 정보를 받는 변수
    lateinit var locationCallback: LocationCallback

/*
    private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
*/
    // fragment 에서 제일 처음 시작되는건 onCreateView(main에서는 onCreate
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var bundle = arguments
        Log.d("아규먼츠1", "${bundle?.getDouble("lat")}, ${bundle?.getDouble("lon")} ")
        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions() ){ results->
            if(!results.all { it.value }){
                Toast.makeText(activity, "권한 승인 필요", Toast.LENGTH_LONG).show()
            }
        }

        // 권한 요청
        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )

        return inflater.inflate(R.layout.fragment_maps, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        updateLocation()

    }

    @SuppressLint("MissingPermission")
    fun updateLocation(){
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        locationCallback = object : LocationCallback(){ // 콜백: 자동호출(trigger)
            // 1초에 한번씩 변경된 위치 정보가 onLocationResult 로 전달된다
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.let {
                    for (location in it.locations){
                        Log.d( "위치정보", " - 위도:${location.latitude} 경도:${location.longitude}")
                        //setLastLocation(location)
                    }
                }
            }
        }

        // 권한 처리
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        Log.d("아규먼츠", "${arguments?.getDouble("lat")}, ${arguments?.getDouble("lon")} ")
        if(arguments?.getDouble("lat") != null && arguments?.getDouble("lon") != null) {
            setLocation(arguments!!.getDouble("lat"), arguments!!.getDouble("lon"))
        }
    }

    fun setLastLocation(lastLocation: Location) {
        val LATLNG = LatLng(lastLocation.latitude, lastLocation.longitude)

        val markerOptions = MarkerOptions().position(LATLNG).title("I am here")
        val cameraPosition = CameraPosition.Builder().target(LATLNG).zoom(15.0f).build()

        mMap.clear()
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    // 본인이 설정한 위도와 경도로 적용하는 경우
    fun setLocation(latitude:Double, longitude:Double) {
        val LATLNG = LatLng(latitude, longitude)

        val markerOptions = MarkerOptions().position(LATLNG).title("I am here")
        val cameraPosition = CameraPosition.Builder().target(LATLNG).zoom(15.0f).build()

        mMap.clear()
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

}