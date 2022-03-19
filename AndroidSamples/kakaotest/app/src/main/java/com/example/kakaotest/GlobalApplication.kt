package com.example.kakaotest

import android.app.Application
import com.kakao.auth.KakaoSDK
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    companion object {
        var instance: GlobalApplication? = null
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        KakaoSDK.init(KakaoSDKAdapder)
    }
}