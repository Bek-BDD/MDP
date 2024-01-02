package com.bow.foodiepal.Stores

import android.content.Context
import android.content.SharedPreferences
import com.bow.foodiepal.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeStore(context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("RecipeStorage", Context.MODE_PRIVATE)
    }

    fun saveRecipes(recipes: MutableList<Recipe>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val recipeList = gson.toJson(recipes)
        editor.putString("Recipe_List", recipeList)
        editor.apply()
    }

    fun getRecipes(): List<Recipe> {
        val gson = Gson()
        val recipeList = sharedPreferences.getString("Recipe_List", null)
        val type = object : TypeToken<List<Recipe>>() {}.type
        return gson.fromJson(recipeList, type) ?: emptyList()
    }
}