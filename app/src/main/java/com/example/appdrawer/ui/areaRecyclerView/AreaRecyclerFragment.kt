package com.example.appdrawer.ui.areaRecyclerView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdrawer.R
import com.example.appdrawer.viewmodel.AreaViewModel
import kotlinx.android.synthetic.main.fragment_area_recycle.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AreaRecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AreaRecyclerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_area_recycle, container, false)

        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter = MyAreaRecyclerViewAdapter(DummyContent.ITEMS, listener)
//            }
//        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: AreaViewModel = ViewModelProvider.NewInstanceFactory().create(
            AreaViewModel::class.java
        )

        viewModel.areas.observe(viewLifecycleOwner,
            Observer {
                it?.let {
                    with(recyclerArea) {
                        //layoutManager = LinearLayoutManager(this@AreaRecycleFragment.context)
                        layoutManager = LinearLayoutManager(context)

                        // Como o parâmetro lambda é o último parâmetro é só abrir e fechar chaves
                        adapter = AreaRecyclerAdapter(it) { it ->
                            getActivity()?.let { fa ->
                                val fragmentManager =fa.supportFragmentManager
                                val fragmentTransaction = fragmentManager.beginTransaction()
                                val fragment = AreaRecyclerDetailFragment.newInstance(it.codigo, it.descricao)
                                //fragmentTransaction.add(R.id.nav_host_fragment, fragment)
                                fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
                                fragmentTransaction.commit()
                            }
                        }
                    }
                }
            }
        )

        viewModel.getAreas()

        progress_bar.visibility = View.VISIBLE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AreaRecycleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AreaRecyclerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
