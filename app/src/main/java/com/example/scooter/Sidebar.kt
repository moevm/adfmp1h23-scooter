package com.example.scooter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView


open class Sidebar(val layout: Int) : AppCompatActivity() {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // write setContentView by init argument of Sidebar
         setContentView(layout)

        drawerLayout = findViewById(R.id.my_drawer_layout)
        navigationView = findViewById(R.id.navigation)
        Log.d("TAG", "onCreate: ${navigationView.menu.size()}")
        for (i in 0 until navigationView.menu.size()) {
            val menuItem = navigationView.menu.getItem(i)
            changeMenuItemColor(menuItem, Color.BLACK)
        }
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        replaceFragment(MapFragment(), "Scooters")
        navigationView.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            drawerLayout.closeDrawer(GravityCompat.START)
            when (id) {
                R.id.nav_home -> {
                    var map = MapFragment()
                    map.addScooters()
                    replaceFragment(map, "Scooters")
//                    val intent = Intent(this, Drawler::class.java)
//                    startActivity(intent)
                    true
                }
                R.id.nav_payment_details -> {
                    Toast.makeText(this, "Payment details", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_my_trips -> {
                    Toast.makeText(this, "My trips", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_logout -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_about -> {
                    replaceFragment(AboutFragment(), "About")
                    true
                }
                else -> {
                    false
                }

            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    private fun changeMenuItemColor(menuItem: MenuItem, @ColorInt color: Int) {
        val coloredMenuItemTitle = SpannableString(menuItem.title)
        coloredMenuItemTitle.setSpan(
            ForegroundColorSpan(color),
            0,
            coloredMenuItemTitle.length,
            0
        )
        menuItem.setTitle(coloredMenuItemTitle)
    }
}