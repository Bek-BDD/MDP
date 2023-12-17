package com.bow.foodiepal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPreferences = getSharedPreferences("FoodiePalPrefs", Context.MODE_PRIVATE)

        val buttonLogin = findViewById<Button>(R.id.loginBtn)
        val editTextUsername = findViewById<EditText>(R.id.emailStr)
        val editTextPassword = findViewById<EditText>(R.id.passStr)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            registerUser(username,password)
            if (validate(username, password)) {

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            } else {

                Toast.makeText(this@LoginActivity, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun authenticate(username: String, password: String): Boolean {
        val sharedPreferences = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
        val savedPassword = sharedPreferences.getString(username, "")
        Toast.makeText(this,savedPassword.toString(),Toast.LENGTH_LONG).show()
        return password == savedPassword.toString()
    }
    private fun registerUser(username:String, password:String){
        sharedPreferences = getSharedPreferences("FoodiePalPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(username, password)
        editor.apply()
    }
    private fun validate(username:String, password:String):Boolean{
        return username.isNotEmpty() && password.isNotEmpty()
    }

}
