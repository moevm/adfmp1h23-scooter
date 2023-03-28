package com.example.scooter

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MapViewModelTest {

    lateinit var viewModel: MapViewModel

    @Before
    fun setUp(){
        viewModel = MapViewModel()
    }

    @Test
    @Tag("UnitTest")
    fun testUserAddedToLocalDb() { // шаг с логином юзера "user1@gmail.com"
        val result = viewModel.getLoginedUser()
        assertEquals(result, "user1@gmail.com")
    }

    @Test
    @Tag("UnitTest")
    fun checkUserCardIsAdded() {  // проверка, привязана ли карта к залогиненому юзеру
        val result = viewModel.checkIfUserAddedCard("user1@gmail.com")
        assertTrue(result)
    }

    @Test
    @Tag("UnitTest")
    fun checkUserCardIsNotAdded() { // проверка, привязана ли карта к незалогиненому юзеру
        val result = viewModel.checkIfUserAddedCard("user2@gmail.com")
        assertFalse(result)
    }
}
