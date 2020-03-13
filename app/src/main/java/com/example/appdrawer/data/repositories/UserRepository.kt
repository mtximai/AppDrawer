package com.example.appdrawer.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appdrawer.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

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

                    var code = response.code().toString()
                    Log.d("mylog", "onResponse code = ${response.code().toString()}")

                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.string()
                    } else {
                        Log.d("mylog", "onResponse.unSuccessful")
                        loginResponse.value = "erro na chamada - code =  $code"
                    }
                }

            })

        return loginResponse
    }

}