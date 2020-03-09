package com.example.appdrawer.ui.test1

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


// Singleton class
object AtomoService {

    val service : IAtomoService = getRetrofitInstance().create(IAtomoService::class.java)

//    fun getAreaCall(): Call<List<Area>> {
//        return service.getAreas()
//    }

    private fun getRetrofitInstance() : Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://mobile.tcm.sp.gov.br/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    interface IAtomoService {
        @GET("api/Corporativo/AreasAtivas")
        fun getAreas(): Call<List<Area>>
    }
}
