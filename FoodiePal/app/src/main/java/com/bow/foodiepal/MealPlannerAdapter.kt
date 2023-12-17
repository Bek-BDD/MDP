package com.bow.foodiepal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealPlannerAdapter(private val items: List<MealPlanItem>) : RecyclerView.Adapter<MealPlannerAdapter.ViewHolder>() {


    // Provide a reference to the type of views that you are using (custom ViewHolder).
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayTextView: TextView = view.findViewById(R.id.dayTextView)
        val bfast: TextView = view.findViewById(R.id.bfastvalue)
        val lunch :TextView = view.findViewById(R.id.lunchvale)
        val diner : TextView = view.findViewById(R.id.dinnervalue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_planner, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.dayTextView.text = item.day
        holder.bfast.text=item.meals.get("Breakfast")
        holder.lunch.text=item.meals.get("Lunch")
        holder.diner.text=item.meals.get("Dinner")

    }

    override fun getItemCount() = items.size
}

