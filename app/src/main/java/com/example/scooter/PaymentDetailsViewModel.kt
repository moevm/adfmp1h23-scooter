package com.example.scooter

import androidx.lifecycle.ViewModel

class PaymentDetailsViewModel : ViewModel() {

    fun addUserCard(cardNumber: String, userName: String, date: String, cvv: String): Boolean {
        return if(
            validateField(cardNumber) && validateField(userName) && validateField(date) && validateField(cvv)
        ) {
            //push data to backend
            true
        } else {
            false
        }
    }

    fun validateField(field: String): Boolean {
        return if (field.isBlank()) {
            false
        } else {
            true
        }
    }
}