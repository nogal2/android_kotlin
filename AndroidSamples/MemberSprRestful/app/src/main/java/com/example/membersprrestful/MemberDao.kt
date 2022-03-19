package com.example.membersprrestful

import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface MemberService {

    @GET("/allMember")
    fun allMember(): Call<List<MemberDto>>  // 웹 서버에 요청을 보내고 응답을 반환하는 Retrofit 메서드 호출

}

class MemberDao {
    companion object{
        var memberDao:MemberDao? = null

        fun getInstance():MemberDao {
            if(memberDao == null) {
                memberDao = MemberDao()
            }
            return memberDao!!
        }

    }

    fun allMember(): ArrayList<MemberDto> {
        val retrofit = RetrofitClient.getInstance()                     //retrofit Singleton 불러옴

        val service = retrofit?.create(MemberService::class.java)       //불러온것을 위 인터페이스 서비스와 연결
        val call = service?.allMember()                                 //service에 있는 함수를 변수에 담아둠.
        val response = call?.execute()                                  //boot로 보낼 코드를 변수에 담아둠.

        return response?.body() as ArrayList<MemberDto>                 // 보낼 변수가 있으면 보내고 리턴값을 받아옴.

    }

}