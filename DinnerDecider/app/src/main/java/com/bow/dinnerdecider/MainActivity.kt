package com.bow.dinnerdecider

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val foodOptions = mutableListOf<String>("Shero", "Meser", "Kitfo", "misto", "mahberawi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)


        val btnAddFood : Button = findViewById(R.id.btn_addFood)
        val btnDecide : Button = findViewById(R.id.btn_decideFood)
        val edtFood : EditText = findViewById(R.id.edit_food)
        val txtDisplay : TextView = findViewById(R.id.txtChosenFood)

        btnDecide.setOnClickListener {
            txtDisplay.text = selectFood(foodOptions);
        }

        btnAddFood.setOnClickListener{
            addFood(edtFood.text.toString())
            edtFood.text.clear()
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
