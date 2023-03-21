package com.example.scooter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.scooter.databinding.FragmentTripsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
data class Item(val name: String, val description: String)
/**
 * A simple [Fragment] subclass.
 * Use the [TripsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TripsFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var itemList: List<Trip>
    private lateinit var binding: FragmentTripsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_trips, container, false)
        listView = view.findViewById(R.id.list_view)
        itemList = getItems()
        listView.adapter = this.context?.let { TripAdapter(it, itemList) }
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this.context, TripDetail::class.java)
            intent.putExtra("date", itemList[position].date)
            intent.putExtra("price", itemList[position].price.toString())
            intent.putExtra("duration", itemList[position].duration.toString())
            startActivity(intent)
        }

        return view
    }

    // Function to get list of items

    private fun getItems(): List<Trip> {
        // TODO: Replace with actual implementation to get items from data source
        return listOf(
            Trip("20.10.23", 100, 5),
            Trip("22.10.23", 200, 10),
            Trip("24.10.23", 300, 2)
        )
    }
}