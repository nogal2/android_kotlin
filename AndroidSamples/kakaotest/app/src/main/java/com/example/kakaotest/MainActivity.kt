package com.example.kakaotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kakaotest.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//카카오 로그인 후, 결과에 대한 코드입니다. token과 error가 null 이 아니라면 me 라는 해당 user의 정보를 가져올 수 있는 함수를 이용하여 kakaoId를 추출하고 token값을 viewModel에게 전달하도록 구현
    val callback: (OAuthToken?, Throwable?) -> Unit = {token, error ->
        if(error!=null) {
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            Log.d("로그인", "로그인 실패")
        } else if (token != null) {
            UserApiClient.instance.me { user, error ->
                val kakaoId = user!!.id
                Toast.makeText(this, "$kakaoId", Toast.LENGTH_SHORT).show()
            }
            Log.d("로그인", "로그인 성공 ")

        }
    }
    val keyHash = Utility.getKeyHash(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // KakaoTalkLoginAvailable을 통해 해당 앱에 카카오톡이 설치되어있는지 확인 후 설치되어있으면 카카오톡 로그인으로, 없다면 카카오 로그인 인터넷 창화면으로 이동하게 하는 코드
        binding.button.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this@MainActivity)) {
                UserApiClient.instance.loginWithKakaoTalk(this@MainActivity, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this@MainActivity,callback=callback)
            }
        }

    }
}