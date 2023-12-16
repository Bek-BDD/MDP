package com.bow.foodiepal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.recipe_name)
        val cookingTimeTextView: TextView = view.findViewById(R.id.recipe_cooking_time)
        val ratingBar: RatingBar = view.findViewById(R.id.recipe_rating)
        val imageView: ImageView = view.findViewById(R.id.recipe_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val recipeView = inflater.inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(recipeView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.nameTextView.text = recipe.name
        holder.cookingTimeTextView.text = recipe.cookingTime
        holder.ratingBar.rating = recipe.rating
        Glide.with(holder.imageView.context).load(recipe.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
