package com.example.appdrawer.ui.areaListView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appdrawer.R
import com.example.appdrawer.viewmodel.AreaViewModel
import kotlinx.android.synthetic.main.fragment_area.*
import kotlinx.android.synthetic.main.fragment_area.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Test1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AreaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_area, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Test1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AreaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.recipe_listview

        //val redcolor = Color.parseColor("#ff0000")
        //listView.setBackgroundColor(R.color.colorPrimary)
        //listView.setBackgroundColor(redcolor)

        //Toast.makeText(view.context, "My message", Toast.LENGTH_SHORT).show()

        val viewModel: AreaViewModel = ViewModelProvider.NewInstanceFactory().create(
            AreaViewModel::class.java)

        viewModel.areas.observe(viewLifecycleOwner,
            Observer {
                it?.let {
                    listView.adapter =
                        AreaAdapter(
                            view.context,
                            it
                        )
                }
            }
        )

        viewModel.getAreas()

        buttonPesquisar.setOnClickListener(View.OnClickListener {
            viewModel.getAreas()
        })

        buttonPesquisarFake.setOnClickListener(View.OnClickListener {
            viewModel.getFakeAreas()
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("myLog","onDestroy() from AreaFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("myLog","onDestroyView() from AreaFragment")
    }
}