package com.example.appdrawer.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appdrawer.R
import com.example.appdrawer.ui.auth.AuthListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), AuthListener {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            text_home.text = it
        })

        homeViewModel.authListener = this

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_HomeSecondFragment)
        }

        buttonLogin.setOnClickListener({
            //Toast.makeText(view.context,"mylog", Toast.LENGTH_SHORT).show()  // works

            homeViewModel.email = "probelalkhan@gmail.com"
            homeViewModel.password = "123456"

            homeViewModel.onLoginButtonClick(view)
        })
    }

    // :::::::::::::::::::::::::::::::::::::::::::
    // ::  Implementatação para AuthListener
    // :::::::::::::::::::::::::::::::::::::::::::

    override fun onStarted() {
        Log.d("mylog", "HomeFragment > onStarted")
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        //Toast.makeText(this.context, "sssss", Toast.LENGTH_SHORT)
        //Snackbar.make(this.view?.context, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()

        Log.d("mylog", "HomeFragment > onSuccess ...")

        loginResponse.observe( viewLifecycleOwner,
            Observer {
                Log.d("mylog", "HomeFragment > loginResponse.observe(): ${it} ")
            }
        )
    }

    override fun onFailure(message: String) {
        //Toast.makeText(this.context, message, Toast.LENGTH_SHORT)
        Log.d("mylog", message)
    }


}
