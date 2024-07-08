package com.example.motofast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TuAdapter : RecyclerView.Adapter<TuAdapter.ViewHolder> (){
    private var datos:List<Products> =  ArrayList()


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtId : TextView = itemView.findViewById(R.id.txtID)
        val txtPrecio : TextView = itemView.findViewById(R.id.txtPrecio)
        val image : ImageView = itemView.findViewById(R.id.imagen)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datos.size
    }
    fun setDatos(datos: List<Products>)
    {
        this.datos=datos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datos [position]
        holder.txtId.text = item.id.toString()
        holder.txtNombre.text = item.nombre
        holder.txtPrecio.text = item.precio.toString()
        Picasso.get().load(item.url).into(holder.image)


    }
}