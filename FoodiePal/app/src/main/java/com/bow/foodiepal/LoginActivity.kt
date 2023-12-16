package com.bow.foodiepal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        sharedPreferences = getSharedPreferences("FoodiePalPrefs", Context.MODE_PRIVATE)
//
//        loginButton.setOnClickListener {
//            val username = usernameEditText.text.toString()
//            val password = passwordEditText.text.toString()
//            if (authenticate(username, password)) {
//                // Proceed to the main activity
//                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                finish()
//            } else {
//                // Show error
//                Toast.makeText(this@LoginActivity, "Authentication failed", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun authenticate(username: String, password: String): Boolean {
//        val storedPassword = sharedPreferences.getString(username, null)
//        return password == storedPassword
//    }
}
