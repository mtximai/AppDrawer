package com.example.appdrawer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appdrawer.entity.Area
import com.example.appdrawer.data.network.AtomoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaViewModel : ViewModel() {

    // PROPRIEDADES
    private val _areas = MutableLiveData<List<Area>>()

    val areas: LiveData<List<Area>> = _areas

    // FUNÇÕES
    fun getAreas() {
        // enqueue() : chamada assíncrona / execute() : chamada síncrona
        AtomoService.service.getAreas().enqueue(object : Callback<List<Area>> {

            override fun onResponse(call: Call<List<Area>>, response: Response<List<Area>>) {
                if (response.isSuccessful) {
                    response.body()?.let { resp ->
                        _areas.value = resp
                    }
                    Log.i("mylog", "AreaViewModel > onResponse: sucesso")
                } else {
                    var msg = response.errorBody().toString()
                    Log.i("mylog", "AreaViewModel > onResponse: $msg")
                }
            }

            override fun onFailure(call: Call<List<Area>>, t: Throwable) {
                Log.d("mylog", "AreaViewModel > onFailure(): ${t.message}")
            }
        })
    }

    fun getFakeAreas() {
        var area1: Area =
            Area("10", "Nova area 10")
        var area2: Area =
            Area("20", "Nova area 20")

        var areaLst: List<Area> = listOf<Area>(area1, area2)

        _areas.value = areaLst
    }
}