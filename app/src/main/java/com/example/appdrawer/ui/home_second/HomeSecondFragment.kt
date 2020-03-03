package com.example.appdrawer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.appdrawer.R

class HomeSecondFragment : Fragment() {

    private lateinit var homeSecondViewModel: HomeSecondViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeSecondViewModel = ViewModelProviders.of(this).get(HomeSecondViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home_second, container, false)

        val textView: TextView = root.findViewById(R.id.text_home_second)

        homeSecondViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }
}
