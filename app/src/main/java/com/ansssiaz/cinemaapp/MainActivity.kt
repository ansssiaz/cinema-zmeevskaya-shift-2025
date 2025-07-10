package com.ansssiaz.cinemaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ansssiaz.cinemaapp.navigation.filmsNavGraph
import com.ansssiaz.component.theme.CinemaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemaAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "films") {
                    filmsNavGraph(navController = navController)
                }
            }
        }
    }
}