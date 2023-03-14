package com.example.scooter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonClickSignUp = findViewById<Button>(R.id.signup_button)
        buttonClickSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        val buttonClickSignIn = findViewById<Button>(R.id.signin_button)
        buttonClickSignIn.setOnClickListener {
            val intent = Intent(this, Drawler::class.java)
            startActivity(intent) }
    }
}