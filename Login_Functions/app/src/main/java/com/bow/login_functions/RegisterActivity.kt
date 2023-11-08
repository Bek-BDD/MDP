package com.bow.login_functions

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bow.login_functions.databinding.SignupBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:SignupBinding;
    private val mainAct =MainActivity();
    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState);
        binding = SignupBinding.inflate(layoutInflater);
         var view =binding.root;

        setContentView(view);

    }

    fun register(view: View) {
        var fname=binding.fnamestr.text.toString();
        var lname=binding.lnamestr.text.toString();
        var email=binding.emailStr.text.toString();
        var pass=binding.passStr.text.toString();

        try {
            var newUser =User(fname,lname,email,pass);
            mainAct.users.add(newUser);
            Toast.makeText(this,"Success, Go to Login page and try",Toast.LENGTH_LONG).show()
             binding.fnamestr.text.clear();
             binding.lnamestr.text.clear();
             binding.emailStr.text.clear();
             binding.passStr.text.clear();
        } catch (e: Exception){
            Toast.makeText(this, "Failed: try again", Toast.LENGTH_LONG).show()
        }


    }

    fun goHome(view:View){
        val intent = Intent(this,MainActivity::class.java);
        startActivity(intent);
    }
}