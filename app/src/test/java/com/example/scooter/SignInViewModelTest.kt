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
    fun testCorrectPasswordAndCorrectEmail() { // Пароль и почтовый адрес введены корректно: пароль длиннее 6 символов, а почтовый адрес содержит символ "@"
        val strongPassword = "1234567"
        val email = "user1@gmail.com"
        val result = viewModel.loginUser(email, strongPassword)
        assertTrue(result)
    }

    @Test

    fun testIncorrectPasswordAndCorrectEmail() {  // Пароль введен некорректно: пароль короче 6 символов
        val strongPassword = "1"
        val email = "user1@gmail.com"
        val result = viewModel.loginUser(email, strongPassword)
        assertFalse(result)
    }

    @Test

    fun testIncorrectPasswordAndIncorrectEmail() {  // Почтовый адрес введен некорректно: почтовый адрес не содержит символ "@"
        val strongPassword = "1"
        val email = "gmail.com"
        val result = viewModel.loginUser(email, strongPassword)
        assertFalse(result)
    }
}
