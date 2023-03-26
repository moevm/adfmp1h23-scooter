package com.example.scooter

import androidx.lifecycle.ViewModel

class MapViewModel : ViewModel() {

    fun checkIfCardAdded(): Boolean {
        val loginedUser = getLoginedUser()
        return checkIfUserAddedCard(loginedUser)
    }

    fun getLoginedUser(): String {
        //request to local db
        return "user1@gmail.com"
    }

    fun checkIfUserAddedCard(email: String): Boolean {
        //request to backend
        return if (email == "user1@gmail.com") {
            true
        } else {
            false
        }
    }
}