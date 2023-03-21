package com.example.scooter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TripDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_detail)

        val date = intent.getStringExtra("date")
        val price = intent.getStringExtra("price")
        val duration = intent.getStringExtra("duration")

        val dateTextView = findViewById<TextView>(R.id.date)
        val priceTextView = findViewById<TextView>(R.id.price)
        val durationTextView = findViewById<TextView>(R.id.duration)

        dateTextView.text = date
        priceTextView.text = price
        durationTextView.text = duration

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            finish()
        }
    }
}