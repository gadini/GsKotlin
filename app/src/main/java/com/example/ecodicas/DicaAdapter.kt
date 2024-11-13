package com.example.ecodicas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DicaAdapter(private val onItemRemoved: (DicaModel) -> Unit) :
    RecyclerView.Adapter<DicaAdapter.DicaViewHolder>() {

    private var dicas = listOf<DicaModel>()

    inner class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textViewTitulo)
        val textViewDesc = view.findViewById<TextView>(R.id.textViewDesc)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(dica: DicaModel) {
            textView.text = dica.titulo
            textViewDesc.text = dica.descricao

            button.setOnClickListener {
                onItemRemoved(dica)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dicas_layout, parent, false)
        return DicaViewHolder(view)
    }

    override fun getItemCount(): Int = dicas.size

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.bind(dica)
    }

    fun updateItems(newDicas: List<DicaModel>) {
        dicas = newDicas
        notifyDataSetChanged()
    }
}