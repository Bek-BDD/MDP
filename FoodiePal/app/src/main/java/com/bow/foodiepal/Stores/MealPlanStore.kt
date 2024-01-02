package com.bow.foodiepal.Stores

import android.content.Context
import android.content.SharedPreferences
import com.bow.foodiepal.MealPlanItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealPlanStore (context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MealPlanPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = object : TypeToken<List<MealPlanItem>>() {}.type
    private val editor = sharedPreferences.edit()

    fun saveMeals(meals:MutableList<MealPlanItem>) {
        val json = gson.toJson(meals)
        editor.putString("mealPlans", json)
        editor.apply()
    }

    fun getMeals(): List<MealPlanItem> {
        val json = sharedPreferences.getString("mealPlans", null)
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}