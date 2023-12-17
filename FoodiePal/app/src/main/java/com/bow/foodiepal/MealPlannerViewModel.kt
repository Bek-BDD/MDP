package com.bow.foodiepal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MealPlannerViewModel : ViewModel() {
    val mealPlanItems = MutableLiveData<List<MealPlanItem>>()

    var items= listOf(
        MealPlanItem("Monday", mapOf(
            "Breakfast" to "Oatmeal with Berries",
            "Lunch" to "Chicken Salad",
            "Dinner" to "Grilled Salmon with Veggies"
        )),
        MealPlanItem("Tuesday", mapOf(
            "Breakfast" to "Greek Yogurt with Honey",
            "Lunch" to "Turkey Sandwich",
            "Dinner" to "Stir-Fried Tofu"
        )),
        MealPlanItem("Wednesday", mapOf(
            "Breakfast" to "Scrambled Eggs with Spinach",
            "Lunch" to "Vegetable Soup",
            "Dinner" to "Beef and Broccoli"
        )),
        MealPlanItem("ሐሙስ", mapOf(
            "Breakfast" to "ቅንጬ",
            "Lunch" to "Vegetable Soup",
            "Dinner" to "Beef and Broccoli"
        )),
        MealPlanItem("አርብ", mapOf(
            "Breakfast" to "ፍርፍር",
            "Lunch" to "ሩዝ በዶሮ",
            "Dinner" to "ፓስታ"
        )),
        MealPlanItem("ቅዳሜ", mapOf(
            "Breakfast" to "ቡላ",
            "Lunch" to "ድንች በስጋ",
            "Dinner" to "ትኩስ ሽሮ ቲማቲም "
        )),
        MealPlanItem("እሁድ", mapOf(
            "Breakfast" to "ጨጭብሳ",
            "Lunch" to "ምስር ን ስላጣ",
            "Dinner" to "አትክልት ሾርባ"
        ))
    )

    fun getMealPlanItems(): LiveData<List<MealPlanItem>> {

        mealPlanItems.value = items
        return mealPlanItems
    }

    fun addMealPlan(plan:MealPlanItem){
        val newPlan = ArrayList(items)
        newPlan.add(plan)
        items=newPlan
    }

}
