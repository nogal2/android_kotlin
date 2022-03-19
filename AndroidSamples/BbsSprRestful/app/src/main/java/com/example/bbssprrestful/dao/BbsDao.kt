package com.example.bbssprrestful.dao

import com.example.bbssprrestful.RetrofitClient
import com.example.bbssprrestful.dto.BbsDto
import retrofit2.Call
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BbsService {

    @GET("/getBbsList")
    fun getBbsList(): Call<ArrayList<BbsDto>>

    @POST("/addWrite")
    fun addWrite(@Body dto: BbsDto): Call<String>

}

class BbsDao {
    companion object{
        var bbsDao:BbsDao? = null

        fun getInstance():BbsDao {
            if(bbsDao == null) {
                bbsDao = BbsDao()
            }
            return bbsDao!!
        }
    }

    fun getBbsList(): ArrayList<BbsDto> {
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsService::class.java)
        val call = service?.getBbsList()
        val response = call?.execute()

        return response?.body() as ArrayList<BbsDto>
    }

    fun addWrite(dataVo:BbsDto):String? {
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(BbsService::class.java)
        val call = service?.addWrite(dataVo)
        val response = call?.execute()

        return response?.body()

    }
}