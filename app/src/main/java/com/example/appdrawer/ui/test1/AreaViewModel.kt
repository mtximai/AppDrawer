package com.example.appdrawer.ui.test1

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
        // _areas.value = getFakeAreas()

        AtomoService.service.getAreas().enqueue(object : Callback<List<Area>> {
            override fun onResponse(call: Call<List<Area>>, response: Response<List<Area>>) {
                if (response.isSuccessful) {
                    _areas.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Area>>, t: Throwable) {
                //Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getFakeAreas() : List<Area> {
        var area1 : Area = Area("10", "Nova area 10")
        var area2 : Area = Area("20", "Nova area 20")

        var areaLst : List<Area> = listOf<Area>(area1, area2)

        return areaLst
    }
}