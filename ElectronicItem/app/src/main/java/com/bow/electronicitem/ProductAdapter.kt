package com.bow.electronicitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bow.electronicitem.databinding.ProductItemLayoutBinding

class ProductAdapter(private val products: List<Product>,
                     private val onAddProduct: (Product) ->
                     Unit, private val onItemClick: (Product) ->
                     Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

        class ProductViewHolder(val binding: ProductItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val binding = ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ProductViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val product = products[position]

            with(holder.binding){
                productName.text = product.productName
                productDescription.text = product.productDescription
                productCost.text = "$${product.cost}"
                productImage.setImageResource(product.productImage)
                addButton.setOnClickListener { onAddProduct(product) }
                root.setOnClickListener {
                    onItemClick(product)
                }

            }

        }

        override fun getItemCount(): Int = products.size

    }