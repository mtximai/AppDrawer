package com.example.appdrawer.ui.test1

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.appdrawer.R
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.fragment_test1.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Test1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Test1Fragment : Fragment() {
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test1, container, false)
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
            Test1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.recipe_listview)
        //listView = getView()!!.findViewById(R.id.recipe_listview) ?:

        //val redcolor = Color.parseColor("#ff0000")
        //listView.setBackgroundColor(R.color.colorPrimary)
        //listView.setBackgroundColor(redcolor)

        //Toast.makeText(view.context, "My message", Toast.LENGTH_SHORT).show()

        listView.adapter = MyCustomAdapter(view.context)

        buttonPesquisar.setOnClickListener(View.OnClickListener {
            getData()
        })

    }

    // 05/03/20
    private class MyCustomAdapter(context: Context) : BaseAdapter() {
        private val mContext: Context = context
        private val names = arrayListOf<String>("Huguinho", "Zezinho", "Luizinho")

        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "Test string"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            /*
            val textView = TextView(mContext)
            textView.text = "dados do item"
            return textView
             */

            val layoutInflater = LayoutInflater.from(mContext)
            val itemView = layoutInflater.inflate(R.layout.listview_item, viewGroup, false)

            val codigoTextView = itemView.findViewById<TextView>(R.id.textCodigo)
            codigoTextView.text = "row number: $position"

            val descricaoTextView = itemView.findViewById<TextView>(R.id.textDescricao)
            descricaoTextView.text = names.get(position)

            return itemView
        }
    }


    fun getData() {
        val retrofitClient = getRetrofitInstance("https://mobile.tcm.sp.gov.br/")

        val endpoint = retrofitClient.create(AtomoService::class.java)
        val callback = endpoint.getAreas()

        callback.enqueue(object : Callback<List<Posts>> {

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                //Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
                textResultado.text = "erro na chamada!!"
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.forEach {
                    //textResultado.text = textResultado.text.toString().plus(it.body)
                    textResultado.text = it.codigo + " " + it.descricao
                }
            }
        })

    }

    private fun getRetrofitInstance(path : String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    data class Posts(
        @SerializedName("CentroCusto")
        var codigo : String,

        @SerializedName("DescricaoCentroCusto")
        var descricao : String
    )

    interface AtomoService {
        @GET("api/Corporativo/AreasAtivas")
        fun getAreas(): Call<List<Posts>>
    }

}