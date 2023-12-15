package com.bow.electronicitem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bow.electronicitem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var productsAdapter: ProductAdapter
    private val cartItems = mutableListOf<Product>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val products =arrayListOf<Product>(
            Product("iPad", "iPad Pro 11-inch", 400.0, R.drawable.ipad),
            Product("MacBook M3 Pro", "12-core CPU\n18-core GPU", 2500.0, R.drawable.macbook),
            Product("Dell Inspiron", "13th Gen Intel® Core™ i7", 1499.0,R.drawable.dell),
            Product("Logitech Keyboard", "Logitech - PRO X\nTKL LIGHTSPEED Wireless", 199.0,R.drawable.logi),
            Product("MacBook M3 Max", "14-core CPU\n30-core GPU", 3499.0,R.drawable.macmax)
        )
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        productsAdapter = ProductAdapter(products,{ product ->
            addToCart(product)
        },{ product ->
            navigateToDetail(product)
        })

        recyclerView.adapter = productsAdapter

        val viewCartButton: Button = binding.viewCartButton
        viewCartButton.setOnClickListener {
            // Show the cart items as a Toast (or implement your own method)
            showCartItems()
        }
    }
    private fun addToCart(product: Product) {
        cartItems.add(product)
        Toast.makeText(this, "${product.productName} added to cart", Toast.LENGTH_SHORT).show()
    }

    private fun showCartItems() {
        val cartItemsNames = cartItems.joinToString(separator = "\n") { it.productName }
       AlertDialog.Builder(this)
           .setMessage("Cart: \n ${cartItemsNames}")
           .setPositiveButton("OK",null)
           .show()
    }
    private fun navigateToDetail(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("PRODUCT_NAME", product.productName)
        intent.putExtra("PRODUCT_DESCRIPTION", product.productDescription)
        intent.putExtra("PRODUCT_COST", product.cost)
        intent.putExtra("PRODUCT_IMAGE",product.productImage)
        startActivity(intent)
    }




}