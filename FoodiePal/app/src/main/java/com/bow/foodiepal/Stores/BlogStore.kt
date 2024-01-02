package com.bow.foodiepal.Stores

import android.content.Context
import android.content.SharedPreferences
import com.bow.foodiepal.Blog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BlogStore (context: Context) {
    private val PREFS_NAME = "BLOG_POSTS"
    private val gson = Gson()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)



    fun savePost(posts: MutableList<Blog> ) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(posts)
        editor.putString(PREFS_NAME, json)
        editor.apply()
    }

    fun getPosts(): List<Blog> {
        val json = sharedPreferences.getString(PREFS_NAME, "")
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<Blog>>() {}.type
            gson.fromJson(json, type)
        }
    }
}