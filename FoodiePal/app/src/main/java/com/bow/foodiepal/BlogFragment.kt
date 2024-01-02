package com.bow.foodiepal

import android.content.SharedPreferences
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
import com.bow.foodiepal.Stores.BlogStore
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BlogFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BlogAdapter
    private lateinit var blogSharedPreferences: BlogStore
    private var blogs = mutableListOf<Blog>()
    private lateinit var viewModel: BlogViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)
        recyclerView = view.findViewById(R.id.blog_recycler_view)
        blogSharedPreferences = BlogStore(requireContext())
        adapter = BlogAdapter(blogs)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(this).get(BlogViewModel::class.java)

        val fab: FloatingActionButton = view.findViewById(R.id.add_recipe_fab)
        fab.setOnClickListener {

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadBlogs()
    }


    private fun loadBlogs() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.blog_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        viewModel.getBlogs().observe(viewLifecycleOwner, Observer { items ->
            blogSharedPreferences.savePost(items.toMutableList())
            recyclerView?.adapter = BlogAdapter(blogSharedPreferences.getPosts())
        })
    }
}
