package com.bow.gardeningjournalapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bow.gardeningjournalapp.R
import com.bow.gardeningjournalapp.entity.Plant

class PlantAdapter(private val plants: List<Plant>,private val onAddPlant: (Plant) ->
Unit, private val onItemClick: (Plant) ->
Unit) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    class PlantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewPlantName)
        val plantTypeTextView = view.findViewById<TextView>(R.id.textViewPlantType)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return PlantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plants[position]
        holder.textViewName.text = plant.name
        holder.plantTypeTextView.text=plant.type
        holder.itemView.setOnClickListener{
            onItemClick(plant)
        }

    }

    override fun getItemCount() = plants.size
}
