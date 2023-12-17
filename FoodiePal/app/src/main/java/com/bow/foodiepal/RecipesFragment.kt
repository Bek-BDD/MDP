package com.bow.foodiepal


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private var recipesList = mutableListOf<Recipe>()
    private lateinit var viewModel: RecipeViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        recyclerView = view.findViewById(R.id.recipes_recycler_view)
        adapter = RecipeAdapter(recipesList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        val fab: FloatingActionButton = view.findViewById(R.id.add_recipe_fab)
        fab.setOnClickListener {
            showAddRecipeDialog()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecipes()
    }
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

        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            val recipeName = dialogView.findViewById<EditText>(R.id.et_recipe_name).text.toString()
            val cookingTime = dialogView.findViewById<EditText>(R.id.et_cooking_time).text.toString()
            val recipe = dialogView.findViewById<EditText>(R.id.et_recipe).text.toString()
            var rating=0f


            ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, ratingbar, _ ->

            }

            if (recipeName.isNotBlank()) {

                viewModel.addRecipe(Recipe(recipesList.size, recipeName, R.drawable.food, cookingTime,recipe, rating))

                dialog.dismiss()
            } else {

                Toast.makeText(context, R.string.recipe_name_required, Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun loadRecipes() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recipes_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        viewModel.getRecipes().observe(viewLifecycleOwner, Observer { items ->
            recyclerView?.adapter = RecipeAdapter(items)
        })
    }
}
