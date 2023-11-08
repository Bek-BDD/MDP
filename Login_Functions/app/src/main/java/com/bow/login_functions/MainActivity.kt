package com.bow.login_functions

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bow.login_functions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;
    var users = ArrayList<User>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        var view =binding.root;
        setContentView(view);

        var user1= User("Bow","Dan","Bow@dan.com","1234abcd");
        var user2 =User("Buta","deju","Buta@deju.com","4321dcba");
        users.add(user1);
        users.add(user2);

    }
    @SuppressLint("SuspiciousIndentation")
    fun onLogin(view : View){
       var username=binding.emailStr.text.toString();
       var password=binding.passStr.text.toString();
       var  isValidUser =false;
        for(user in users){
            if( user.email() == username){
                isValidUser=true;
            if(user.password()==password){
                val intent = Intent(this, ShoppingActivity::class.java);
                    intent.putExtra("username",username);
                    startActivity(intent);
                return;
                }
                else{
                    Toast.makeText(this, "Not a valid Password",Toast.LENGTH_LONG).show();
                return;
                }
            }
        }
        if(!isValidUser){
            Toast.makeText(this, "Not a valid User, Please Register",Toast.LENGTH_LONG).show();
        }
    }

    fun onForgotPass(view:View){
        var username=binding.emailStr;
        var pass=binding.passStr;
        var body =R.string.body.toString()+ pass.text.toString();
        if(username.text.isNotEmpty()){
            val intent=Intent(Intent.ACTION_SENDTO);
            intent.data= Uri.parse("mailto:${username.text.toString()}");
            intent.putExtra(Intent.EXTRA_SUBJECT,R.string.sub);
            intent.putExtra(Intent.EXTRA_TEXT,body);
        }
        try{
            startActivity(Intent.createChooser(intent,"Send Email"))
        }catch (e: ActivityNotFoundException){
            Toast.makeText(this,"No Email App Found",Toast.LENGTH_LONG).show();
        }

    }

    fun onCreateAccount(view: View){
        val intent = Intent(this,RegisterActivity::class.java);
        startActivity(intent);
    }





}