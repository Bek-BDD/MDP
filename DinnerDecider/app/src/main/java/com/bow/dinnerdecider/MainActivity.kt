package com.bow.dinnerdecider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bow.dinnerdecider.databinding.MainPageBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : MainPageBinding
    private val foodOptions = mutableListOf<String>("Shero", "Meser", "Kitfo", "misto", "mahberawi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=MainPageBinding.inflate(layoutInflater);
        var view= binding.root;
        setContentView(view)


        val addFoodBtn =binding.addFoodBtn;
        val decideBtn =binding.decideBtn;
        val customOrder = binding.customOrder;
        val display = binding.display

        decideBtn.setOnClickListener {
            display.text = selectFood(foodOptions);
        }

        addFoodBtn.setOnClickListener{
            addFood(customOrder.text.toString())
            display.text = customOrder.text;
            customOrder.text.clear()

        }
    }

    private fun selectFood (foods : List<String>): String{
        val selectedIndex = Random.nextInt(0, foods.size)
        return foodOptions[selectedIndex];
    }

    private fun addFood (foodItem : String){
        foodOptions.add(foodItem);
    }
    }
