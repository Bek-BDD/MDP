package com.bow.foodiepal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity(){
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val username = findViewById<EditText>(R.id.emailStr)
        val password = findViewById<EditText>(R.id.passStr)
        val signUpButton = findViewById<Button>(R.id.loginBtn)

        signUpButton.setOnClickListener {
            val usernameStr = username.text.toString()
            val passwordStr = password.text.toString()

            if (validateInput(usernameStr, passwordStr)) {
                editor.putString("USERNAME", usernameStr)
                editor.putString("PASSWORD", passwordStr)
                editor.apply()

                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))

                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        if (username.isEmpty() ) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 4) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}