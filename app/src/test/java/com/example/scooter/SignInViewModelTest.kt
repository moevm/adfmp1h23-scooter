package com.example.scooter

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SignInViewModelTest {

    lateinit var viewModel: SignInViewModel

    @Before
    fun setUp(){
        viewModel = SignInViewModel()
    }

    @Test
    fun testCorrectPasswordAndCorrectEmail() {
        val strongPassword = "1234567"
        val email = "user1@gmail.com"
        val result = viewModel.loginUser(email, strongPassword)
        assertTrue(result)
    }

    @Test
    fun testIncorrectPasswordAndCorrectEmail() {
        val strongPassword = "1"
        val email = "user1@gmail.com"
        val result = viewModel.loginUser(email, strongPassword)
        assertFalse(result)
    }

    @Test
    fun testIncorrectPasswordAndIncorrectEmail() {
        val strongPassword = "1"
        val email = "gmail.com"
        val result = viewModel.loginUser(email, strongPassword)
        assertFalse(result)
    }
}