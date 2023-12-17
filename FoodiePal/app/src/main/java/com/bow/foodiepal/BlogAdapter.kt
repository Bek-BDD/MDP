package com.bow.foodiepal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BlogAdapter(private val blogs: List<Blog> ):
    RecyclerView.Adapter<BlogAdapter.ViewHolder>() {
    private lateinit var context :Context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.findViewById(R.id.authorName)
        val postTime: TextView = view.findViewById(R.id.postedTime)
        val post: TextView = view.findViewById(R.id.post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         context= parent.context
        val inflater = LayoutInflater.from(context)
        val recipeView = inflater.inflate(R.layout.item_blog, parent, false)
        return ViewHolder(recipeView)
    }

    override fun onBindViewHolder(holder: BlogAdapter.ViewHolder, position: Int) {

        val blog = blogs[position]
        holder.author.text =blog.author
        holder.postTime.text = blog.postTime
        holder.post.text= context.getString(R.string.post2)
    }



    override fun getItemCount(): Int {
        return blogs.size
    }
}