package com.example.appdrawer.ui.home

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.appdrawer.data.repositories.UserRepository
import com.example.appdrawer.ui.auth.AuthListener
import kotlinx.android.synthetic.main.fragment_area_recycler.*

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

    // --------------------

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null


    fun onLoginButtonClick(view: View) {
        // works!
        //Toast.makeText(view.context, "HomeViewModel > onLoginButtonClick", Toast.LENGTH_SHORT).show();

        authListener?.onStarted()

        // Apply validation rules
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        // todo: use DI to inject UserRepository
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}