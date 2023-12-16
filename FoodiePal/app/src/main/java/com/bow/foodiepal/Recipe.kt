package com.bow.foodiepal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe (
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String,
        val image: String,
        val cookingTime: String,
        val rating: Float
    ){

}