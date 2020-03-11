package com.example.appdrawer.ui.areaRecyclerView

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.appdrawer.R
import com.example.appdrawer.entity.Area
import kotlinx.android.synthetic.main.area_item.view.*

// onItemClickListener: Recebe um lambda para call back para a próxima tela
class AreaRecyclerAdapter(
    private val areas: List<Area>,
    val onItemClickListener: ((area: Area) -> Unit)
) : RecyclerView.Adapter<AreaRecyclerAdapter.AreaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.area_item, parent, false)
        return AreaViewHolder(view)
    }

    // O layout dos itens devem ser atualizado aqui
    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.bindView(areas[position])
    }

    override fun getItemCount(): Int = areas.size

    inner class AreaViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {
        private val codigo = mItemView.textCodigo
        private val descr = mItemView.textDescricao

        fun bindView(area: Area) {
            codigo.text = area.codigo
            descr.text = area.descricao

            mItemView.setOnClickListener {
                onItemClickListener.invoke(area)
                //Snackbar.make(mView, "Replace with your own action ${area.descricao}", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        }
    }

}