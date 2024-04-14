package com.example.kunstapp

import androidx.navigation.NavController
import org.junit.Assert.assertEquals
import org.junit.Test

@Test
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}

