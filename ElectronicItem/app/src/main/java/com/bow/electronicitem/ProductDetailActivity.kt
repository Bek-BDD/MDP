package com.bow.electronicitem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bow.electronicitem.databinding.ActivityMainBinding
import com.bow.electronicitem.databinding.ActivityProductDetailBinding
import com.bow.electronicitem.databinding.ProductItemLayoutBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION")
        val productCost = intent.getDoubleExtra("PRODUCT_COST", 0.0)
        val productImage =  intent.getIntExtra("PRODUCT_IMAGE",0)


        binding.productName.text = productName
        binding.productDescription.text = productDescription
        binding.productCost.text = "$$productCost"
        binding.productImage.setImageResource(productImage)
        binding.productLogo.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent);}
    }



}