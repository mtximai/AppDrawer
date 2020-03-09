package com.example.appdrawer.ui.test1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaViewModel : ViewModel() {
    private val _areas = MutableLiveData<List<Area>>()

    val areas: LiveData<List<Area>> = _areas

    fun getAreas() {
        AtomoService.service.getAreas().enqueue(object : Callback<List<Area>> {
            override fun onResponse(call: Call<List<Area>>, response: Response<List<Area>>) {
                var msg = response.errorBody().toString()

                if (response.isSuccessful) {
                    _areas.value = response.body()
                    Log.i("mylog","AreaViewModel > getAreas: sucesso")
                }
                else {
                    Log.i("mylog","AreaViewModel > getAreas: $msg")
                }
            }

            override fun onFailure(call: Call<List<Area>>, t: Throwable) {
                Log.d("mylog","AreaViewModel > onFailure(): erro ")
            }

        })
    }

    fun getFakeAreas() {
        var area1 : Area = Area("10", "Nova area 10")
        var area2 : Area = Area("20", "Nova area 20")

        var areaLst : List<Area> = listOf<Area>(area1, area2)

        _areas.value = areaLst
    }
}