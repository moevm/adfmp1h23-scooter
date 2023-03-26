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
    fun testUserAddedToLocalDb() {
        //шаг с логином юзера
        val result = viewModel.getLoginedUser()
        assertEquals(result, "user1@gmail.com")
    }

    @Test
    fun checkUserCardIsAdded() {
        val result = viewModel.checkIfUserAddedCard("user1@gmail.com")
        assertTrue(result)
    }

    @Test
    fun checkUserCardIsNotAdded() {
        val result = viewModel.checkIfUserAddedCard("user2@gmail.com")
        assertFalse(result)
    }
}