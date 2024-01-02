package com.bow.foodiepal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("USERNAME", null)
        val savedPassword = sharedPreferences.getString("PASSWORD", null)

        val username = findViewById<EditText>(R.id.emailStr)
        val password = findViewById<EditText>(R.id.passStr)
        val loginButton = findViewById<Button>(R.id.loginBtn)
        val signUpText = findViewById<TextView>(R.id.createBtn)

        loginButton.setOnClickListener {
            if (username.text.toString() == savedUsername && password.text.toString() == savedPassword) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        signUpText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
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
