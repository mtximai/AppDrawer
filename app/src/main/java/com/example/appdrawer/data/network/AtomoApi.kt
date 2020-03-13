package com.example.appdrawer.data.network

import com.example.appdrawer.entity.Area
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface AtomoApi {

    @GET("api/Corporativo/AreasAtivas")
    fun getAreas(): Call<List<Area>>

    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    //        @GET("/users/{user}")
    //        fun getUser(@Path("user") userId: String): Call<User>


    //    @FormUrlEncoded
    //    @POST("login")
    //    fun userLogin(
    //        @Field("email") email: String,
    //        @Field("password") password: String
    //    ): Call<ResponseBody>

    companion object {

        operator fun invoke() : AtomoApi {
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
                .create(AtomoApi::class.java)
        }

    }
}