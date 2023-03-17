package com.example.scooter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yandex.mapkit.MapKitFactory
import io.github.cdimascio.dotenv.dotenv

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env"
        }
        MapKitFactory.setApiKey(dotenv["YANDEX_MAPS_API_KEY"])
        val buttonClick = findViewById<Button>(R.id.signup_button)
        buttonClick.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        val loginBtn = findViewById<Button>(R.id.signin_button)
        loginBtn.setOnClickListener {
            val intent = Intent(this, Drawler::class.java)
            startActivity(intent)
        }
    }
}