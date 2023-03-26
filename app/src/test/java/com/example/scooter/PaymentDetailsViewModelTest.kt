package com.example.scooter

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PaymentDetailsViewModelTest {

    lateinit var viewModel: PaymentDetailsViewModel

    @Before
    fun setUp(){
        viewModel = PaymentDetailsViewModel()
    }

    @Test
    fun testCorrectInput() {
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertTrue(result)
    }

    @Test
    fun testIncorrectInput1() {
        val card = ""
        val name = "User User"
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    fun testIncorrectInput2() {
        val card = "1234 1234 1234 1234"
        val name = ""
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    fun testIncorrectInput3() {
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = ""
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    fun testIncorrectInput4() {
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = "01/01/24"
        val cvv = ""
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }
}