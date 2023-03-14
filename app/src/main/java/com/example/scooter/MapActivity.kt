package com.example.scooter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.GeoObject
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
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
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env"
        }
        MapKitFactory.setApiKey(dotenv["YANDEX_MAPS_API_KEY"])
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_map)
        this.mapview = findViewById<View>(R.id.mapview) as MapView
        mapview.getMap().move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
        for (location in scooter_location_list) {
            mapview.map.mapObjects.addPlacemark(
                location, ImageProvider.fromResource(
                    this, R.drawable.kick_scooter
                )
            ).addTapListener { _, _ ->
                Log.d("Marker", "Latitude: ${location.latitude}, Longitude: ${location.longitude}")
                Toast.makeText(this, "Latitude: ${location.latitude}, Longitude: ${location.longitude}", Toast.LENGTH_LONG).show()
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
}