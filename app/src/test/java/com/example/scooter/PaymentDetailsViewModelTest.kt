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
    @Tag("UnitTest")
    fun testCorrectInput() {
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertTrue(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectCardInput() {
        val card = ""
        val name = "User User"
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectUserNameInput() {
        val card = "1234 1234 1234 1234"
        val name = ""
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectDateInput() {
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = ""
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectCVVInput() {
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = "01/01/24"
        val cvv = ""
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }
}
