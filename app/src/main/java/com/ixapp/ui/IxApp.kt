package com.ixapp.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun IxApp() {
    val navController = rememberNavController()
    MaterialTheme {
        // NavHost and screens will be placed here in full project
    }
}
