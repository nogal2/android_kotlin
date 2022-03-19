package com.example.sample22fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMenu : Fragment() {

    // 프래그먼트 상속한 후 맴버 재정의 컨트롤o 하고 함수 찾기, 그리고 activity_fragment_menu 연결결
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_fragment_menu, container, false)
    }
}