package com.example.appdrawer.ui.test1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Singleton class
object AtomoService {

    val service : IAtomoService = getRetrofitInstance().create(IAtomoService::class.java)

//    fun getAreaCall(): Call<List<Area>> {
//        return service.getAreas()
//    }

    private fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mobile.tcm.sp.gov.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    interface IAtomoService {
        @GET("api/Corporativo/AreasAtivas")
        fun getAreas(): Call<List<Area>>
    }
}
