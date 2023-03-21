package com.example.scooter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment


class TripAdapter(context: Context, private val arrayList: List<Trip>) :
    ArrayAdapter<Trip>(context, R.layout.list_item) {

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.d("getView", "getView called")
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item, null)

        val date = view.findViewById<TextView>(R.id.date)
        val price = view.findViewById<TextView>(R.id.price)
        val duration = view.findViewById<TextView>(R.id.duration)

        date.text = arrayList[position].date
        price.text = arrayList[position].price.toString()
        duration.text = arrayList[position].duration.toString()
        Log.d("view", view.toString())
        return view
    }
}