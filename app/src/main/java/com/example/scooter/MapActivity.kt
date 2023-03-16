package com.example.scooter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import com.yandex.mapkit.Animation
import com.yandex.mapkit.GeoObject
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import io.github.cdimascio.dotenv.dotenv


class MapActivity : AppCompatActivity() {
    private lateinit var mapview: MapView
    private val TARGET_LOCATION = Point(59.972271, 30.323405)
    private val scooter_location_list = listOf<Point>(
        Point(59.972271, 30.323405),
        Point(59.970634, 30.319071),
        Point(59.971843, 30.319832),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_map)
        this.mapview = findViewById<View>(R.id.mapview) as MapView
        mapview.getMap().move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.LINEAR, 1f),
            null
        )
        for (location in scooter_location_list) {
            val mapObj = mapview.map.mapObjects.addPlacemark(
                location, ImageProvider.fromResource(
                    this, R.drawable.kick_scooter
                )
            )
            mapObj.zIndex = 1f
            mapObj.addTapListener { obj, point ->
                onScooterClick(obj, point)
                true
            }
        }
    }

    override fun onStop() {
        mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapview.onStart()
    }

    private fun onScooterClick(mapObject: MapObject, point: Point): Boolean {
        val scooterInfo = findViewById<View>(R.id.scooter_info) as LinearLayout
        scooterInfo.isVisible = true
        val startButton = findViewById<View>(R.id.start_button)
        val stopButton = findViewById<View>(R.id.stop_button)
        startButton.setOnClickListener {
            findViewById<View>(R.id.scooter_info_text_start).isVisible = false
            findViewById<View>(R.id.scooter_info_text_stop).isVisible = true
            startButton.isVisible = false
            stopButton.isVisible = true
        }
        stopButton.setOnClickListener {
            findViewById<View>(R.id.scooter_info_text_start).isVisible = true
            findViewById<View>(R.id.scooter_info_text_stop).isVisible = false
            startButton.isVisible = true
            stopButton.isVisible = false
            scooterInfo.isVisible = false
        }
        Log.d("Marker", point.toString())
        return true
    }
}