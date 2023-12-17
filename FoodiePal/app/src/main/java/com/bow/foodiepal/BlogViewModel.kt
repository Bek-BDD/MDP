package com.bow.foodiepal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlogViewModel : ViewModel() {
    private val blogs = MutableLiveData<List<Blog>>()
    var items= listOf(
                Blog("John Bulis","09/6/23",R.string.post2,4f),
                Blog("Kuajien pal","01/17/23",R.string.post2,5f),
                Blog("Dagim Bre","11/14/23",R.string.post2,4.5f)
    )
    fun getBlogs(): LiveData<List<Blog>> {
        blogs.value=items
        return blogs
    }
    fun addBlog(blog:Blog){
        val newItem =ArrayList(items)

        newItem.add(blog)
        items= newItem
    }
}