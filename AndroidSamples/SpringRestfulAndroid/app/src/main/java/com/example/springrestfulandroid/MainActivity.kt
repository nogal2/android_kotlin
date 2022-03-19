package com.example.springrestfulandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import com.example.springrestfulandroid.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class MemberDto(val id:String, val pwd:String, val name:String, val email:String, val auth:Int)

interface ReadyService {

    // 1. 문자열 받기
    @GET("/base")
    fun base(): Call<String>

    // 2. 문자열 보내고 받기
    @GET("/connParamGet")
    fun connParamGet(@Query("title") title:String): Call<String>    // 보내는 데이터를 @Query 로 잡아준다.
    // title 은 spring boot 에 매개변수와 같아야한다.

    // 3. object 를 보내고 받기
    @POST("/getMember")
    fun getMember(@Body mem: MemberDto): Call<MemberDto>  // POST 일때는 @Body 를 써야한다.
    // spring boot 에서는 매개변수에 @RequestBody 를 넣어줘야한다.

    // 4. DB 데이터 받기.
    @GET("/dbTest")
    fun dbTest(): Call<List<MemberDto>>


}

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Network 처리 추가한다 == httpURLConnection
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Gson은 Java객체를 JSON으로 변환할 수 있다.
        val gson = GsonBuilder()
                    .setLenient()
                    .create()

        val retrofit = Retrofit.Builder()
                        .baseUrl("http://192.168.219.102:3000/")
                        .addConverterFactory(GsonConverterFactory.create(gson))     // object, integer
                        .addConverterFactory(ScalarsConverterFactory.create())      // 문자열 리턴 받는 경우
                        .build()

        // 위에 있는 interface를 상속해서 생성해주는 코드가 여기있다.
        val service = retrofit.create(ReadyService::class.java)

        // 1. 문자열 받기
        //val call = service.base()

        // 2. 문자열 보내고 받기
        //val call = service.connParamGet("제목입니다")

        // 3. object 를 보내고 받기
        //val call = service.getMember(MemberDto("aaa", "111", "AAA", "aaa@aaa.a", 3))

        // 4. DB 데이터 받기
        val call = service.dbTest()     // 값을 변수에 담아둠. dbTest에는 보낼 데이터는 없어서 따로 값은 없지만 해야한다.

        val response = call.execute()   // 값 보냄

        if(response.isSuccessful) {     // 값 보낸 게 성공했을때

            if(response.code() == 200) {    // 성공했을때(200은 성공한 숫자, 404는 실패한 숫자)

                // 1. 문자열 받기
                /*val base: String? = response.body()
                println("base: $base")*/


                // 2. 문자열 보내고 받기
                /*val str:String? = response.body()
                println("str:$str")*/

                // 3. object 를 보내고 받기
               /* val dto:MemberDto? = response.body()
                println(dto)*/

                // 4. DB 데이터 받기
                val list:List<MemberDto>? = response.body()     // spring boot에서 보내는 값 받음
                println(list.toString())
            }
        }
    }
}