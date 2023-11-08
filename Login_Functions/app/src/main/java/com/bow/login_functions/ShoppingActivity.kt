package com.bow.login_functions

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bow.login_functions.databinding.ShoppingBinding

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ShoppingBinding;

    override fun onCreate (savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState);
        binding = ShoppingBinding.inflate(layoutInflater);
        var view = binding.root;
        setContentView(view);

        val username=intent.getStringExtra("username");
       var wecome= binding.welcome;
        wecome.text= wecome.text.toString() + " "+username;
    }

    fun buy(view: View) {
        Toast.makeText(this,"Horray! your pick is Amazing, Enjoy!!!",Toast.LENGTH_LONG).show()
    }

    fun goHome(view:View){
        val intent = Intent(this,MainActivity::class.java);
        startActivity(intent);
    }


}