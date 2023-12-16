package com.bow.foodiepal

data class Meal(
    val id: Int,
    val date: String, // Format: "yyyy-MM-dd"
    val mealType: String, // e.g., "Breakfast", "Lunch", "Dinner"
    val description: String
)
