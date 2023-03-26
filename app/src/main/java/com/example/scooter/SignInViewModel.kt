package com.example.scooter

import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    fun loginUser(email: String, password: String): Boolean {
        return if(checkEmailIsValid(email) && checkPasswordIsValid(password)){
            true
        } else {
            false
        }
    }

    private fun checkEmailIsValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun checkPasswordIsValid(password: String): Boolean {
        val size = password.length
        return size >= 6
    }
}
