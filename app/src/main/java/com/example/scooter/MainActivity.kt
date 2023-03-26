package com.example.scooter

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.yandex.mapkit.MapKitFactory
import io.github.cdimascio.dotenv.dotenv

class MainActivity : AppCompatActivity() {

    val signInViewModel: SignInViewModel by viewModels()

    lateinit var loginEt: EditText
    lateinit var passwordEt: EditText

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginEt = findViewById(R.id.username_edittext)
        passwordEt = findViewById(R.id.password_edittext)
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env.template"
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
            val email = loginEt.text.toString()
            val password = passwordEt.text.toString()
            val successfullyLogined = signInViewModel.loginUser(email, password)

            if (successfullyLogined) {
                Log.d("mytag", "Login successfull")
                val intent = Intent(this, Sidebar::class.java)
                startActivity(intent)
            } else {
                Log.d("mytag", "Can not login")
            }
        }
    }
}
