package com.bow.foodiepal


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private var recipesList = mutableListOf<Recipe>()


    private fun showAddRecipeDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.activity_add_recipe, null)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.rating_bar)
        val builder = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .setTitle(R.string.add_new_recipe)
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(R.string.cancel, null)
        }

        val dialog = builder?.show()

        // Override the positive button here to prevent the dialog from closing on click
        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            val recipeName = dialogView.findViewById<EditText>(R.id.et_recipe_name).text.toString()
            val cookingTime = dialogView.findViewById<EditText>(R.id.et_cooking_time).text.toString()
            var rating=0f


            ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, ratingbar, _ ->

            }
            Toast.makeText(context, "postive",Toast.LENGTH_LONG).show()

            if (recipeName.isNotBlank()) {
                // Save the recipe. This is where you'd have some logic to actually save the data
                // For example, add it to your list and notify the adapter
                recipesList.add(Recipe(recipesList.size, recipeName, "", cookingTime, rating))
                adapter.notifyItemInserted(recipesList.size - 1)


                // Dismiss the dialog
                dialog.dismiss()
            } else {
                // Notify the user if the recipe name is blank
                Toast.makeText(context, R.string.recipe_name_required, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        recyclerView = view.findViewById(R.id.recipes_recycler_view)
        adapter = RecipeAdapter(recipesList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val fab: FloatingActionButton = view.findViewById(R.id.add_recipe_fab)
        fab.setOnClickListener {
            showAddRecipeDialog()
        }

        // Here you would load the actual recipes data into the list
        loadRecipes()

        return view
    }

    private fun loadRecipes() {
        // Populate the recipes list with actual data
        // This is where you might fetch from a database or a REST API
    }
}
