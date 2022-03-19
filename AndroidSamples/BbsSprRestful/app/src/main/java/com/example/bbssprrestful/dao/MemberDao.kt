package com.example.bbssprrestful.dao


import com.example.bbssprrestful.RetrofitClient
import com.example.bbssprrestful.dto.MemberDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface MemberService {

    @POST("/login")
    fun login(@Body dto: MemberDto): Call<MemberDto>

}

class MemberDao {
    companion object {
        var memberDao: MemberDao? = null

        fun getInstance():MemberDao {
            if(memberDao == null) {
                memberDao = MemberDao()
            }
            return memberDao!!
        }
    }

    var dto = MemberDto("","","","", 3)

    fun login(dataVo:MemberDto): MemberDto {

        val retrofit = RetrofitClient.getInstance()
        try {
            val service = retrofit?.create(MemberService::class.java)
            val call = service?.login(dataVo)
            val response = call?.execute()

            dto = response?.body() as MemberDto
        } catch (e:Exception){}
        return dto


    }

}