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
    fun testCorrectInput() {  // Все данные карты введены корректно: номер карты, имя владельца карты, дата выпуска карты и CVV код.
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertTrue(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectCardInput() { // Не введен номер карты
        val card = ""
        val name = "User User"
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectUserNameInput() { // Не введено имя владельца карты
        val card = "1234 1234 1234 1234"
        val name = ""
        val date = "01/01/24"
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectDateInput() {   // Не введена дата выпуска карты
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = ""
        val cvv = "000"
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }

    @Test
    @Tag("UnitTest")
    fun testIncorrectCVVInput() {  // Не введен CVV код
        val card = "1234 1234 1234 1234"
        val name = "User User"
        val date = "01/01/24"
        val cvv = ""
        val result = viewModel.addUserCard(card, name, date, cvv)
        assertFalse(result)
    }
}
