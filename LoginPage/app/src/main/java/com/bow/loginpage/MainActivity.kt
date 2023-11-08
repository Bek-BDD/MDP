package com.bow.loginpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bow.loginpage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater);
        var view=binding.root;
        setContentView(view);

        var forgot=binding.forgotP

        forgot.setOnClickListener{
            Toast.makeText(this,"hey",Toast.LENGTH_LONG).show();
        }
    }


}