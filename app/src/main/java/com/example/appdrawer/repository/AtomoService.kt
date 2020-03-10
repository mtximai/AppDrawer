package com.example.appdrawer.repository

import com.example.appdrawer.entity.Area
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


// Singleton class
object AtomoService {

    val service : IAtomoService = getRetrofitInstance()
        .create(IAtomoService::class.java)

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

        /**
         * @GET declares an HTTP GET request
         * @Path("user") annotation on the userId parameter marks it as a
         * replacement for the {user} placeholder in the @GET path
         */
        //        @GET("/users/{user}")
        //        fun getUser(@Path("user") userId: String): Call<User>
    }
}
