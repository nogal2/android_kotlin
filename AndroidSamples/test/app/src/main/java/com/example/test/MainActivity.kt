package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.test.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        thread(start=true) {    // 서브 스레드로 동작하게함. onCreate 에서 thread.sleep 을 실행하면 메인쓰레드가 실행되기 때문에 전체가 멈춤.
                                // 그래서 UI와 관련된 모든 것은 메인스레드에서 동작해야한다.
                                // 하지만 서브스레드로 하면 이 안에 있는 것들만 3초동안 멈추게 할 수 있다.
            Thread.sleep(3000)  // 3000 밀리초 = 3초, 서브스레드로 3초 동안 동작을 멈춤
            runOnUiThread {     // 메인 스레드
                showProgress(false)
            }

            // 이렇게 앱을 실행하면 3초간 프로그래스 바가 동작하다가 없어진다.
        }
    }

    fun showProgress(show: Boolean) {
        if(show) {
            binding.progressLayout.visibility = View.VISIBLE    // 화면 나타남
        } else {
            binding.progressLayout.visibility = View.GONE       // 화면 사라짐(invisible 은 안보이는 상태이지만 공간을 차치함. gone은 공간도 차지하지 않음)
        }
    }
}