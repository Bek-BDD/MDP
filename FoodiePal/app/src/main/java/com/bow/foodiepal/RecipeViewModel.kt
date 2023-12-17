package com.bow.foodiepal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {
    private val recipe = MutableLiveData<List<Recipe>>()
    var items= listOf(
        Recipe(0,"Chechebsa",R.drawable.food1, "20mins","make you food using this",4.5f),
        Recipe(0,"Genfo",R.drawable.food4, "35mins","make you food using this",4f),
        Recipe(0,"Shero",R.drawable.food3, "20mins","make you food using this",4.2f),
        Recipe(0,"meser",R.drawable.food2, "30mins","make you food using this",5f),
        Recipe(0,"kitfo",R.drawable.food5, "20mins","make you food using this",4.8f)
    )
    fun getRecipes(): LiveData<List<Recipe>>{
        recipe.value=items
        return recipe
    }
    fun addRecipe(recipe:Recipe){
        val newItem =ArrayList(items)

        newItem.add(recipe)
        items= newItem
    }
}