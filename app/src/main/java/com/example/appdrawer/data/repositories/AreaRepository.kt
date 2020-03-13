package com.example.appdrawer.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appdrawer.data.network.AtomoApi
import com.example.appdrawer.data.network.MyApi
import com.example.appdrawer.entity.Area
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaRepository {

    fun userLogin(email: String, password: String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        // todo: utilizar Dependency Injection para criar o MyApi
        MyApi().userLogin(email, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.string()
                    } else {
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

            })

        return loginResponse
    }

    // FUNÇÕES
    fun getAreas() : LiveData<List<Area>> {

        val areas = MutableLiveData<List<Area>>()

        // enqueue() : chamada assíncrona
        // execute() : chamada síncrona
        AtomoApi().getAreas().enqueue(object : Callback<List<Area>> {

            override fun onResponse(call: Call<List<Area>>, response: Response<List<Area>>) {

                if (response.isSuccessful) {
                    response.body()?.let { resp ->
                        areas.value = resp
                    }

                    Log.d("mylog", "AreaRepository > onReponse / successful")

                } else {
                    var msg = response.errorBody().toString()
                    Log.d("mylog", "AreaRepository > onReponse / unSuccessful: ${msg}")
                }
            }

            override fun onFailure(call: Call<List<Area>>, t: Throwable) {
                Log.d("mylog", "AreaRepository > onFailure(): ${t.message}")
            }
        })

        return areas
    }

    fun getFakeAreas() : LiveData<List<Area>> {

        val areas = MutableLiveData<List<Area>>()

        var area1: Area =
            Area("10", "Nova area 10")
        var area2: Area =
            Area("20", "Nova area 20")

        var areaLst = listOf<Area>(area1, area2)

        areas.value = areaLst

        return areas
    }

}