package com.example.kakaologin

import android.app.Application
import android.content.Context
import com.kakao.auth.KakaoSDK
import com.kakao.sdk.common.KakaoSdk

class KakaoApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "${getString(R.string.kakao_native_key)}")
    }
}