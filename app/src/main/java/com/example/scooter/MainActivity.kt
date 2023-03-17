package com.example.scooter

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import com.yandex.mapkit.MapKitFactory
import io.github.cdimascio.dotenv.dotenv

class MainActivity : AppCompatActivity() {
    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env"
        }
        try {
            MapKitFactory.setApiKey(dotenv["YANDEX_MAPS_API_KEY"])
        } catch (e: AssertionError) {
            Log.d("MapKitFactory already initialized", e.toString())
        }

        val buttonClick = findViewById<Button>(R.id.signup_button)
        buttonClick.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        val loginBtn = findViewById<Button>(R.id.signin_button)
        loginBtn.setOnClickListener {
            val intent = Intent(this, Sidebar::class.java)
            startActivity(intent)
        }
    }
}