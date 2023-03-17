package com.example.scooter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


class MapFragment : Fragment() {
    private lateinit var mapview: MapView
    private val TARGET_LOCATION = Point(59.972271, 30.323405)
    private val scooter_location_list = listOf<Point>(
        Point(59.972271, 30.323405),
        Point(59.970634, 30.319071),
        Point(59.971843, 30.319832),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MapKitFactory.initialize(this.context)
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        this.mapview = view.findViewById<View>(R.id.mapview) as MapView
        mapview.getMap().move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.LINEAR, 1f),
            null
        )
        this.addScooters()
        return view
    }

    fun addScooters() {
        for (location in scooter_location_list) {
            val mapObj = mapview.map.mapObjects.addPlacemark(
                location, ImageProvider.fromResource(
                    this.context, R.drawable.kick_scooter
                )
            )
            mapObj.zIndex = 1f
            mapObj.addTapListener { obj, point ->
                onScooterClick(obj, point)
                true
            }
        }
    }

    private fun onScooterClick(mapObject: MapObject, point: Point): Boolean {
        val view = getView() as FrameLayout
        val scooterInfo = view.findViewById<View>(R.id.scooter_info) as LinearLayout
        scooterInfo.isVisible = true
        val startButton = view.findViewById<View>(R.id.start_button)
        val stopButton = view.findViewById<View>(R.id.stop_button)
        startButton.setOnClickListener {
            view.findViewById<View>(R.id.scooter_info_text_start).isVisible = false
            view.findViewById<View>(R.id.scooter_info_text_stop).isVisible = true
            startButton.isVisible = false
            stopButton.isVisible = true
        }
        stopButton.setOnClickListener {
            view.findViewById<View>(R.id.scooter_info_text_start).isVisible = true
            view.findViewById<View>(R.id.scooter_info_text_stop).isVisible = false
            startButton.isVisible = true
            stopButton.isVisible = false
            scooterInfo.isVisible = false
        }
        Log.d("Marker", point.toString())
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}