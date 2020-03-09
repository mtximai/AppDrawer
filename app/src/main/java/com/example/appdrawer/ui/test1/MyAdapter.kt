package com.example.appdrawer.ui.test1

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.appdrawer.R

// 05/03/20 - Adapter para ListView
// todo: Remover dependência da Activity via passagem do Context
class MyAdapter(context: Context,
                private val areas: List<Area>) : BaseAdapter() {

    private val mContext: Context = context

    override fun getCount(): Int {
        return areas.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return areas[position]
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        /*
        val textView = TextView(mContext)
        textView.text = "dados do item"
        return textView
         */

        val layoutInflater = LayoutInflater.from(mContext)
        val itemView = layoutInflater.inflate(R.layout.listview_item, viewGroup, false)

        val codigoTextView = itemView.findViewById<TextView>(R.id.textCodigo)
        val codigo = areas[position].codigo
        codigoTextView.text = "Código: ${areas[position].codigo}"

        val descricaoTextView = itemView.findViewById<TextView>(R.id.textDescricao)
        descricaoTextView.text = areas[position].descricao

        // Captura o toque no item
        itemView.setOnClickListener {
            Toast.makeText(mContext, "My message: $codigo", Toast.LENGTH_SHORT).show()
        }

        return itemView
    }
}
