package com.example.scooter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val buttonClick = findViewById<Button>(R.id.continue_button)

        buttonClick.setOnClickListener {
            val intent = Intent(this, Sidebar::class.java)
            startActivity(intent)
        }
    }
}
